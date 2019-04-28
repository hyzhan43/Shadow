package com.example.core.bean.card;

import com.example.core.bean.db.Auth;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/22
 * desc：    TODO
 */
@Data
public class AuthCard {

    String module;

    String auth;

    public AuthCard(Auth auth) {
        this.module = auth.getModule();
        this.auth = auth.getAuth();
    }
}
