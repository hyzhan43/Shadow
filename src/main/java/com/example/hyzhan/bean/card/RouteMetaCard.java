package com.example.hyzhan.bean.card;

import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/9
 * desc：    TODO
 */
@Data
public class RouteMetaCard {

    // 权限
    private String auth;
    // 模块
    private String module;
    // 是否挂载
    private boolean mount;
}
