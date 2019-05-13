package com.zhan.app.error.handle;

import com.zhan.app.error.APIException;
import com.zhan.core.bean.BaseResponse;
import com.zhan.core.bean.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public BaseResponse handle(APIException e) {
        return Response.error(e.getCode(), e.getMsg());
    }
}
