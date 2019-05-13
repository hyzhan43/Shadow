package com.zhan.core.interceptor.auth.handle;

import com.zhan.core.bean.db.User;
import com.zhan.core.error.BaseException;
import com.zhan.core.error.code.ErrorCode;
import com.zhan.core.interceptor.auth.AuthHandle;
import com.zhan.core.service.UserService;
import com.zhan.core.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/5/13
 * desc：    TODO
 */
@Component
public class AdminHandle implements AuthHandle {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpServletRequest request, String methodName) {
        TokenUtils.verifyToken(request);

        User user = userService.getUserById(Integer.parseInt(TokenUtils.uid));

        if (!user.isSuper()) {
            throw new BaseException(ErrorCode.ADMIN_ERROR);
        }
    }
}
