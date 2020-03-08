package com.wyz.coffee.lang.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MArg {
    String key();

    String value();
}
