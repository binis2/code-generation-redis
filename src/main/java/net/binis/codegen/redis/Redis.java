package net.binis.codegen.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import net.binis.codegen.annotation.CodeConfiguration;
import net.binis.codegen.exception.MapperException;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.map.Mapper;
import net.binis.codegen.redis.encoding.ValueEncoding;

import static java.util.Objects.nonNull;
import static net.binis.codegen.redis.encoding.ValueEncoding.STRING;

@CodeConfiguration
public class Redis {

    protected static final RedisClient client = RedisClient.create();
    protected static StatefulRedisConnection<String, Object> connection;
    protected static ValueEncoding encoding;

    protected static String connectionString;

    public static RedisClient client() {
        return client;
    }

    public static StatefulRedisConnection<String, Object> connection() {
        return connection;
    }

    public static StatefulConnection<String, Object> setup(String connection) {
        return setup(connection, STRING);
    }

    @SuppressWarnings("unchecked")
    public static StatefulConnection<String, Object> setup(String connection, ValueEncoding encoding) {
        Redis.connectionString = connection;
        Redis.encoding = encoding;
        switch (encoding) {
            case STRING -> Redis.connection = (StatefulRedisConnection) client.connect(StringCodec.UTF8, RedisURI.create(connection));
            case BYTE_ARRAY -> Redis.connection = (StatefulRedisConnection) client.connect(RedisCodec.of(StringCodec.UTF8, ByteArrayCodec.INSTANCE), RedisURI.create(connection));
            default -> throw new UnsupportedOperationException();
        }

        return Redis.connection;
    }

    @SuppressWarnings("unchecked")
    public static <T> T load(String key, Class<T> cls) {
        var impl = CodeFactory.lookup(cls);
        if (nonNull(impl)) {
            var result = connection.sync().get(getPrefix(impl) + key);
            if (nonNull(result)) {
                return (T) Mapper.convert(result, impl, key);
            } else {
                return null;
            }
        }

        return null;
    }

    public static <T> T load(Class<T> cls) {
        return load("", cls);
    }

    public static Object serialize(Object obj) {
        return switch (encoding) {
            case STRING -> {
                try {
                    yield CodeFactory.create(ObjectMapper.class).writeValueAsString(obj);
                } catch (Exception e) {
                    throw new MapperException(e);
                }
            }
            case BYTE_ARRAY -> Mapper.convert(obj, byte[].class);
            default -> throw new UnsupportedOperationException();
        };
    }

    private static String getPrefix(Class<?> impl) {
        try {
            return (String) impl.getDeclaredField("PREFIX").get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Redis() {
    }

}
