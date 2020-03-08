package com.wyz.coffee.lang.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MLog {
    String title() default "MLog";

    MArg[] value() default {};

    boolean afterThrow() default false;
}
