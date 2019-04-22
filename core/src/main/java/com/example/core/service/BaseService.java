package com.example.core.service;

import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import org.apache.catalina.connector.Request;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public class BaseService {

    // 获取当前 user id
    protected String getCurrentUid() {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes());

        if (attributes == null){
            throw new BaseException(ErrorCode.USER_ERROR);
        }

        HttpServletRequest request = attributes.getRequest();
        return (String) request.getAttribute("uid");
    }
}
