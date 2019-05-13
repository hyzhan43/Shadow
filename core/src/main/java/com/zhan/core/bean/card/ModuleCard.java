package com.zhan.core.bean.card;

import lombok.Data;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/22
 * desc：    TODO
 */
@Data
public class ModuleCard {
    // 模块
    private String module;

    private List<AuthCard> auths;

    public ModuleCard(String module, List<AuthCard> auths) {
        this.module = module;
        this.auths = auths;
    }
}
