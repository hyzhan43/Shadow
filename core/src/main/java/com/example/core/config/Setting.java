package com.example.core.config;

/**
 * author：  HyZhan
 * create：  2019/4/5
 * desc：    通用配置
 */
public class Setting {

    // 起始页
    public static final int PAGE = 0;
    // 一页的数量
    public static final int PAGE_SIZE = 10;

    // token 过期时间
    public static final int TOKEN_TIME = 60 * 60 * 1000;
    // refreshToken 过期时间
    public static final int REFRESH_TOKEN_TIME = 15 * 24 * 60 * 60 * 1000;
}
