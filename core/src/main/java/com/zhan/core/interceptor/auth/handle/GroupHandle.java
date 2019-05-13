package com.zhan.core.interceptor.auth.handle;

import com.zhan.core.bean.db.User;
import com.zhan.core.error.BaseException;
import com.zhan.core.error.code.ErrorCode;
import com.zhan.core.interceptor.auth.AuthHandle;
import com.zhan.core.service.AuthService;
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
public class GroupHandle implements AuthHandle {

    private AuthService authService;

    private UserService userService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpServletRequest request, String methodName) {
        TokenUtils.verifyToken(request);

        User user = userService.getUserById(Integer.parseInt(TokenUtils.uid));

        if (user.isForbid()) {
            throw new BaseException(ErrorCode.ACTIVE_ERROR);
        }

        if (!user.isSuper()) {
            Integer groupId = user.getGroupId();
            if (groupId == null) {
                throw new BaseException(ErrorCode.GROUP_EMPTY);
            }

            authService.isUserAllowed(groupId, methodName);
        }
    }
}
