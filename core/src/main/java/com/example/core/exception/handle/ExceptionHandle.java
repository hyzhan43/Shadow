package com.example.core.exception.handle;

import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.utils.L;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


/**
 * author：  HyZhan
 * create：  2018/11/6 21:00
 * desc：    全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    // 捕获自定义异常   响应状态码 -> 200
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseResponse handle(BaseException e) {
        return Response.error(e.getErrorCode());
    }

    // 捕获 token 异常  状态码 -> 200
    @ExceptionHandler({SignatureException.class, ExpiredJwtException.class})
    @ResponseBody
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

    // 没有传 requestBody 会抛出此异常
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResponse methodHandle(BindException e) {
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
