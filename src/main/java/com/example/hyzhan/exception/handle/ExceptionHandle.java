package com.example.hyzhan.exception.handle;

import com.example.hyzhan.bean.BaseResponse;
import com.example.hyzhan.bean.Response;
import com.example.hyzhan.exception.BaseException;
import com.example.hyzhan.exception.code.ErrorCode;
import com.example.hyzhan.utils.Log;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import static com.example.hyzhan.exception.code.ErrorCode.TOKEN_ERROR;


/**
 * author：  HyZhan
 * create：  2018/11/6 21:00
 * desc：    全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    // 捕获自定义异常   响应状态码 -> 200
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handle(Exception e) {
        if (e instanceof BaseException) {
            BaseException exception = (BaseException) e;
            return Response.error(exception.getErrorCode());
        }

        // 没有传 requestBody 会抛出此异常
        if (e instanceof HttpMessageNotReadableException) {
            return Response.error(ErrorCode.PARAMETER);
        }

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = exception.getBindingResult();
            // 获取参数错误信息
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                String msg = fieldError.getDefaultMessage();
                return Response.error(msg);
            }
        }

        if (e instanceof SignatureException || e instanceof ExpiredJwtException){
            return Response.error(ErrorCode.TOKEN_ERROR);
        }

        Log.error("-------------------------------------------------");
        Log.error(e + "");
        Log.error("-------------------------------------------------");

        return Response.error(ErrorCode.UNKNOWN_ERROR);
    }
}
