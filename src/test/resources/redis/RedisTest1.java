package net.binis.codegen.redis.test;

import net.binis.codegen.redis.annotation.CodeRedis;

import java.util.List;

@CodeRedis("base:")
public interface RedisTestPrototype {
    String title();

    int data();

    List<String> list();

}