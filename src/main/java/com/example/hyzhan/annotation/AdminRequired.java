package com.example.hyzhan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    被 AdminRequired 注解方法只有超级管理员才可访问
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminRequired {
}