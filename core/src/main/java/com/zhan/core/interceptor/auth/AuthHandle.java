package com.zhan.core.interceptor.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/5/13
 * desc：    TODO
 */
public interface AuthHandle {

    void handle(HttpServletRequest request, String methodName);
}
