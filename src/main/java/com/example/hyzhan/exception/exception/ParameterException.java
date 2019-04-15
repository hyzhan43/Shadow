package com.example.hyzhan.exception.exception;

import com.example.hyzhan.exception.BaseException;
import com.example.hyzhan.exception.code.ErrorCode;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public class ParameterException extends BaseException {

    public ParameterException() {
        super(ErrorCode.PARAMETER);
    }
}
