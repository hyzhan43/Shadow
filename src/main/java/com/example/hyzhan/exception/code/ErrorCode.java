package com.example.hyzhan.exception.code;

/**
 * author：  HyZhan
 * create：  2018/11/7 18:01
 * desc：    错误 枚举类
 */
public enum ErrorCode {
    UNKNOWN_ERROR(-1, "服务内部错误，不想告诉你~"),
    HTTP_METHOD_ERROR(-2, "Http 请求方法不匹配!"),
    PARAMETER(-3, "参数错误")
    ;

    private Integer code;
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
