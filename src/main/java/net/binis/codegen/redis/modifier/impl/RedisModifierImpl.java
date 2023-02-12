package net.binis.codegen.redis.modifier.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.binis.codegen.exception.MapperException;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.modifier.impl.BaseModifierImpl;
import net.binis.codegen.redis.Redis;
import net.binis.codegen.redis.modifier.RedisModifier;

import java.lang.reflect.Method;
import java.util.function.Consumer;

@Slf4j
public abstract class RedisModifierImpl<T, R> extends BaseModifierImpl<T, R> implements RedisModifier<T, R> {

    protected Method keyMethod;

    protected RedisModifierImpl(R parent) {
        super(parent);
        try {
            keyMethod = parent.getClass().getDeclaredMethod("key");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public R save() {
        return withKey(key ->
                Redis.connection().sync().set(key, Redis.serialize(parent)));
    }

    @Override
    public R delete() {
        return withKey(key -> {
            try {
                Redis.connection().sync().del(key);
            } catch (Exception e) {
                throw new MapperException(e);
            }
        });
    }


    protected R withKey(Consumer<String> consumer) {
        try {
            var k = (String) keyMethod.invoke(parent);
            consumer.accept(k);
            return parent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
