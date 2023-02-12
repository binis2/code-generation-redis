package net.binis.codegen.redis.test;

import net.binis.codegen.redis.Redis;
import net.binis.codegen.test.TestExecutor;
import static org.junit.jupiter.api.Assertions.*;


public class Execute extends TestExecutor {

    @Override
    public boolean execute() {

        var obj = RedisTest.create("test").title("yay").data(5).done();
        assertEquals("base:test", obj.key());
        assertEquals("yay", obj.getTitle());
        assertEquals(5, obj.getData());

        return true;
    }
}
