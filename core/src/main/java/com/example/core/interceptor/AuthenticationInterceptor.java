package com.example.core.interceptor;

import com.example.core.annotation.AdminRequired;
import com.example.core.annotation.GroupRequired;
import com.example.core.annotation.LoginRequired;
import com.example.core.bean.db.User;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.service.auth.AuthService;
import com.example.core.service.user.UserService;
import com.example.core.utils.JWTUtils;
import com.example.core.utils.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    权限身份拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        L.error("拦截器执行啦~~~~~~~~");
        L.error("url->" + request.getRequestURL().toString());


        // 如果不是映射方法 直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断方法是否有  @LoginRequired
        if (method.isAnnotationPresent(LoginRequired.class)) {
            verifyToken(request);
            return true;
        }

        // 被 adminRequired 装饰的视图函数只有超级管理员才可访问
        if (method.isAnnotationPresent(AdminRequired.class)) {
            String uid = verifyToken(request);

            User user = userService.findById(Integer.parseInt(uid));

            if (user.getAdmin() != User.COMMON) {
                throw new BaseException(ErrorCode.ADMIN_ERROR);
            }

            return true;
        }

        // 判断方法是否有  @LoginRequired
        if (method.isAnnotationPresent(GroupRequired.class)) {
            String uid = verifyToken(request);

            User user = userService.findById(Integer.parseInt(uid));
            if (user.getActive() != User.ACTIVE){
                throw new BaseException(ErrorCode.ACTIVE_ERROR);
            }

            if (user.getAdmin() != User.COMMON) {
                throw new BaseException(ErrorCode.ADMIN_ERROR);
            }

            Integer groupId = user.getGroupId();
            if (groupId == null){
                throw new BaseException(ErrorCode.GROUP_EMPTY);
            }

            authService.isUserAllowed(groupId, method.getName());

            return true;
        }

        return true;
    }

    private String verifyToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            throw new BaseException(ErrorCode.TOKEN_EMPTY);
        }

        String uid = prepareCachedValue(token);
        request.setAttribute("uid", uid);

        return uid;
    }

    private String prepareCachedValue(String token) {

        String uid = JWTUtils.parseJwt(token);

        if (uid == null || uid.isEmpty()) {
            throw new BaseException(ErrorCode.TOKEN_ERROR);
        }

        return uid;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {


        L.error("执行了");
    }
}
