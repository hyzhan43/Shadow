package com.example.core.bean.card;

import com.example.core.bean.db.Auth;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    public ModuleCard(List<AuthCard> auths) {

        if (auths.isEmpty()) {
            return;
        }

        this.module = auths.get(0).getModule();
        this.auths = auths;
    }

    public ModuleCard(String module, List<AuthCard> auths) {
        this.module = module;
        this.auths = auths;
    }
}
