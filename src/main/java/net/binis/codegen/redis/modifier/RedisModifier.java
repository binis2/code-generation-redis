package net.binis.codegen.redis.modifier;

import net.binis.codegen.modifier.BaseModifier;

public interface RedisModifier<T, R> extends BaseModifier<T, R> {

    R save();
    R delete();

}
