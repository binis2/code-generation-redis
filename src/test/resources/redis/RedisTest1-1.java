/*Generated code by Binis' code generator.*/
package net.binis.codegen.redis.test;

import net.binis.codegen.redis.modifier.RedisModifier;
import net.binis.codegen.redis.Redis;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.collection.CodeList;
import net.binis.codegen.annotation.Default;
import javax.annotation.processing.Generated;
import java.util.List;

@Generated(value = "net.binis.codegen.redis.test.RedisTestPrototype", comments = "RedisTestImpl")
@Default("net.binis.codegen.redis.test.RedisTestImpl")
public interface RedisTest {

    // region starters
    static RedisTest.Modify create(String key) {
        return CodeFactory.create(RedisTest.class, key).with();
    }
    // endregion

    int getData();
    List<String> getList();
    String getTitle();

    String key();

    // region starters
    static RedisTest load(String key) {
        return Redis.load(key, RedisTest.class);
    }

    static RedisTest load() {
        return Redis.load(RedisTest.class);
    }
    // endregion

    RedisTest.Modify with();

    // region inner classes
    interface Fields<T> {
        T data(int data);
        T title(String title);
    }

    interface Modify extends RedisTest.Fields<RedisTest.Modify>, RedisModifier<RedisTest.Modify, RedisTest> {
        Modify list(List<String> list);
        CodeList<String, RedisTest.Modify> list();
    }
    // endregion
}
