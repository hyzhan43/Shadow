package com.example.core.bean;

import com.example.core.bean.card.ResponseCard;
import com.example.core.error.code.ErrorCode;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2018/11/6 20:41
 * desc：    TODO
 */
public class Response {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    public static String getRequestUrl() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getMethod() + " " + request.getRequestURI();
        }

        return "";
    }

    public static <T> ResponseCard success(T msg) {
        return success(msg, getRequestUrl());
    }

    public static <T> ResponseCard success(T msg, String request) {
        return error(SUCCESS, msg, request);
    }

    public static <T> ResponseCard error(Integer code, T msg, String request) {
        ResponseCard<T> responseCard = new ResponseCard<>();
        responseCard.setErrorCode(code);
        responseCard.setMsg(msg);
        responseCard.setRequest(request);
        return responseCard;
    }

    public static BaseResponse error(ErrorCode state) {
        BaseResponse response = new BaseResponse();
        response.setCode(state.getCode());
        response.setMsg(state.getMsg());
        return response;
    }

    public static ResponseCard error(ErrorCode state, String requestInfo) {
        ResponseCard<String> responseCard = new ResponseCard<>();
        responseCard.setErrorCode(state.getCode());
        responseCard.setMsg(state.getMsg());
        responseCard.setRequest(requestInfo);
        return responseCard;
    }

    public static BaseResponse error(String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(ERROR);
        response.setMsg(message);
        return response;
    }
}
