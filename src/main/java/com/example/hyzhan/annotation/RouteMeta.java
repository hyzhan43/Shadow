package com.example.hyzhan.annotation;

import java.lang.annotation.*;

/**
 * author：  HyZhan
 * create：  2019/4/9
 * desc：    TODO
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RouteMeta {

    String auth();

    String module();

    boolean mount() default true;
}
