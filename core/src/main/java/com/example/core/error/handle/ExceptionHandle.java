package com.example.core.error.handle;

import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.utils.L;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * author：  HyZhan
 * create：  2018/11/6 21:00
 * desc：    全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    // 捕获自定义异常   响应状态码 -> 200
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public BaseResponse handle(BaseException e) {
        return Response.error(e.getErrorCode());
    }

    // 捕获 token 异常  状态码 -> 200
    @ResponseBody
    @ExceptionHandler({SignatureException.class, ExpiredJwtException.class})
    public BaseResponse tokenHandle(Exception e) {
        // jwt 校验失败
        return Response.error(ErrorCode.TOKEN_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse paramsHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 获取参数错误信息
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            String msg = fieldError.getDefaultMessage();
            return Response.error(msg);
        }

        return Response.error(ErrorCode.UNKNOWN_ERROR);
    }

    // 没有传 requestBody 会抛出此异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public BaseResponse methodHandle(HttpMessageNotReadableException e) {
        return Response.error(ErrorCode.PARAMETER);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResponse methodHandle(BindException e) {
        if (e.hasErrors() && e.getFieldError() != null) {
            String msg = e.getFieldError().getDefaultMessage();
            return Response.error(msg);
        }

        return Response.error(ErrorCode.PARAMETER);
    }


    // 请求 http 方法不对
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public BaseResponse methodHandle(HttpRequestMethodNotSupportedException e) {
        return Response.error(ErrorCode.HTTP_METHOD_ERROR);
    }


    // 捕获 服务器内部异常  状态码 -> 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handle(Exception e) {

        L.error("-------------------------------------------------");
        L.error(e + "");
        L.error("-------------------------------------------------");

        return Response.error(ErrorCode.UNKNOWN_ERROR);
    }
}
