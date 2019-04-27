package com.example.app.error;

import com.example.app.error.code.APICode;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
public class APIException extends RuntimeException{

    private APICode apiCode;

    public APIException(APICode apiCode) {
        this.apiCode = apiCode;
    }

    public Integer getCode() {
        return apiCode.getCode();
    }

    public String getMsg() {
        return apiCode.getMsg();
    }
}
