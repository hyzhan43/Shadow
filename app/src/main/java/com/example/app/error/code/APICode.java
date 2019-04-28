package com.example.app.error.code;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
public enum APICode {
    BOOK_NOT_FOUND(2000, "没有找到相关书籍"),
    BOOK_IS_EXIST(2001, "图书已存在"),
    ;

    private Integer code;
    private String msg;

    APICode(Integer code, String msg) {
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
