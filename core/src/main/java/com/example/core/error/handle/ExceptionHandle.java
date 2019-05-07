package com.example.core.error.handle;

import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.bean.card.ResponseCard;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.utils.L;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public ResponseCard handle(HttpServletRequest request, BaseException e) {
        return Response.error(e.getErrorCode(), decorateUrl(request));
    }


    /**
     *  没有传递 @RequestParam
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseCard handle(HttpServletRequest request, MissingServletRequestParameterException e) {
        return Response.error(ErrorCode.PARAMETER, decorateUrl(request));
    }


    /**
     * 捕获 token 异常  状态码 -> 401
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({SignatureException.class, ExpiredJwtException.class})
    public ResponseCard tokenHandle(HttpServletRequest request) {
        // jwt 校验失败
        return Response.error(ErrorCode.TOKEN_ERROR, decorateUrl(request));
    }

    /**
     * valid + requestBody 注解校验出错抛出的异常处理类
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseCard paramsHandle(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 获取参数错误信息
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> errorMap = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return Response.error(ErrorCode.PARAMETER.getCode(), errorMap, decorateUrl(request));
    }

    /**
     * 没有传 @requestBody 参数会抛出此异常
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, HttpMediaTypeNotSupportedException.class})
    public ResponseCard methodHandle(HttpServletRequest request) {
        return Response.error(ErrorCode.REQUEST_BODY_ERROR, decorateUrl(request));
    }


    /**
     * 请求 http 方法不对
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseCard methodHandle(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        return Response.error(ErrorCode.HTTP_METHOD_ERROR, decorateUrl(request));
    }

    /**
     * 捕获 服务器内部异常  状态码 -> 500
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handle(Exception e) {

        L.error("-------------------------------------------------");
        L.error(e + "");
        L.error("-------------------------------------------------");

        return Response.error(ErrorCode.UNKNOWN_ERROR);
    }


    private String decorateUrl(HttpServletRequest request) {
        return request.getMethod() + " " + request.getRequestURI();
    }
}
