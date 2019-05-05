package com.example.app.error.handle;

import com.example.core.bean.card.ResponseCard;
import com.example.app.error.APIException;
import com.example.core.bean.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@ControllerAdvice
public class APIExceptionHandle {

    // 捕获api自定义异常   响应状态码 -> 200
    @ExceptionHandler(APIException.class)
    @ResponseBody
    public ResponseCard handle(HttpServletRequest request, APIException e) {
        String requestInfo = request.getMethod() + " " + request.getRequestURI();
        return Response.error(e.getCode(), e.getMsg(), requestInfo);
    }
}
