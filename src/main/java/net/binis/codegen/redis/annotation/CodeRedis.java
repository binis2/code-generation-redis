package net.binis.codegen.redis.annotation;

import net.binis.codegen.annotation.CodePrototype;
import net.binis.codegen.annotation.CodePrototypeTemplate;
import net.binis.codegen.annotation.validation.AliasFor;
import net.binis.codegen.enrich.*;
import net.binis.codegen.redis.modifier.RedisModifier;

@CodePrototypeTemplate
@CodePrototype(
        interfaceSetters = false,
        baseModifierClass = RedisModifier.class,
        enrichers = {
                RedisEnricher.class,
                ValidationEnricher.class,
                RegionEnricher.class})
public @interface CodeRedis {

    @AliasFor("prefix")
    String value() default "";
    String prefix() default "";

}
