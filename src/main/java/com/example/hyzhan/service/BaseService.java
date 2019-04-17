package com.example.hyzhan.service;

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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();

        return (String) request.getAttribute("uid");
    }
}
