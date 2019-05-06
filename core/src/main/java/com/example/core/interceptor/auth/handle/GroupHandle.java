package com.example.core.interceptor.auth.handle;

import com.example.core.bean.db.User;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.interceptor.auth.AuthHandle;
import com.example.core.service.AuthService;
import com.example.core.service.UserService;
import com.example.core.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/5/5
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
