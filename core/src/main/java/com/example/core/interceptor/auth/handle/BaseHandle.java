package com.example.core.interceptor.auth.handle;

import com.example.core.bean.db.User;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.service.UserService;
import com.example.core.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/5/5
 * desc：    TODO
 */
public class BaseHandle {

    private String uid;

    @Autowired
    private UserService userService;

    void verifyToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            throw new BaseException(ErrorCode.TOKEN_EMPTY);
        }

        prepareCachedValue(token);

        request.setAttribute("uid", uid);
    }

    private void prepareCachedValue(String token) {

        uid = TokenUtils.parseToken(token);

        if (uid == null || uid.isEmpty()) {
            throw new BaseException(ErrorCode.TOKEN_ERROR);
        }
    }

    User getCurrentUser() {
        return userService.getUserById(Integer.parseInt(uid));
    }
}
