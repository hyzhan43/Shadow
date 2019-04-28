package com.example.core.error;

import com.example.core.error.code.ErrorCode;

/**
 * author：  HyZhan
 * create：  2018/11/6 21:07
 * desc：    TODO
 */

public class BaseException extends RuntimeException {

    private ErrorCode errorCode;

    public BaseException(ErrorCode ErrorCode) {
        super(ErrorCode.getMsg());
        this.errorCode = ErrorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
