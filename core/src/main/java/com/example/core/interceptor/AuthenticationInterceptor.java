package com.example.core.interceptor;

import com.example.core.annotation.AdminRequired;
import com.example.core.annotation.GroupRequired;
import com.example.core.annotation.Logger;
import com.example.core.annotation.LoginRequired;
import com.example.core.bean.args.LogInfoArgs;
import com.example.core.interceptor.auth.AuthFactory;
import com.example.core.interceptor.auth.handle.AdminHandle;
import com.example.core.interceptor.auth.handle.GroupHandle;
import com.example.core.interceptor.auth.handle.LoginHandle;
import com.example.core.resource.LogResource;
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
    LogResource logResource;

    @Autowired
    LoginHandle loginHandle;

    @Autowired
    AdminHandle adminHandle;

    @Autowired
    GroupHandle groupHandle;

    @Autowired
    AuthFactory authFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        L.i("url->" + request.getRequestURL().toString());

        // 如果不是映射方法 直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(LoginRequired.class)) {
            authFactory.setHandle(loginHandle);
        } else if (method.isAnnotationPresent(AdminRequired.class)) {
            authFactory.setHandle(adminHandle);
        } else if (method.isAnnotationPresent(GroupRequired.class)) {
            authFactory.setHandle(groupHandle);
        } else {
            // 默认没有以上注解，不进行拦截
            return true;
        }

        authFactory.handle(request, method.getName());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        // 如果不是映射方法 直接通过
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(Logger.class)) {

            // 判断是否搭配@AdminRequired、@GroupRequired 使用
            if (!(method.isAnnotationPresent(LoginRequired.class)
                    || method.isAnnotationPresent(AdminRequired.class))) {

                L.error(method.getName() + "方法记录日志失败，应搭配@AdminRequired、@GroupRequired");
                return;
            }

            Logger logger = method.getAnnotation(Logger.class);

            LogInfoArgs args = new LogInfoArgs();

            String uid = (String) request.getAttribute("uid");
            args.setUserId(Integer.parseInt(uid));
            args.setMethodName(method.getName());
            args.setTemplate(logger.template());
            args.setStatusCode(response.getStatus());
            args.setMethod(request.getMethod());
            args.setPath(request.getRequestURI());

            logResource.saveLog(args);
        }
    }
}
