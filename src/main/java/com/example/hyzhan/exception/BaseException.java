package com.example.hyzhan.exception;

import com.example.hyzhan.exception.code.ErrorCode;

/**
 * author：  HyZhan
 * create：  2018/11/6 21:07
 * desc：    TODO
 */

public class BaseException extends RuntimeException {

    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode =  errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
