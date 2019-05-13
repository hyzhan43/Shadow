package com.zhan.core.interceptor.auth;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/5/13
 * desc：    权限处理工厂 (策略模式)
 */
@Component
public class AuthFactory {

    private AuthHandle authHandle;

    public void setHandle(AuthHandle authHandle) {
        this.authHandle = authHandle;
    }

    public void handle(HttpServletRequest request, String methodName) {
        authHandle.handle(request, methodName);
    }
}
