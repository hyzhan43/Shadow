package com.example.core.interceptor.auth.handle;

import com.example.core.interceptor.auth.AuthHandle;
import com.example.core.utils.TokenUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/5/5
 * desc：    TODO
 */
@Component
public class LoginHandle implements AuthHandle {

    @Override
    public void handle(HttpServletRequest request, String methodName) {
        TokenUtils.verifyToken(request);
    }
}
