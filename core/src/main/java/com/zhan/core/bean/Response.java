package com.zhan.core.bean;

import com.zhan.core.error.code.ErrorCode;

/**
 * author：  HyZhan
 * create：  2018/11/6 20:41
 * desc：    TODO
 */
public class Response {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    private static final String MSG_SUC = "获取成功";

    public static <T> BaseResponse<T> success(String msg, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(SUCCESS);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static BaseResponse success() {
        return success(null);
    }

    public static BaseResponse success(String msg) {
        if (msg == null) {
            msg = MSG_SUC;
        }
        return success(msg, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return success(MSG_SUC, data);
    }

    public static BaseResponse error(Integer code, String msg) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static BaseResponse error(ErrorCode state) {
        BaseResponse response = new BaseResponse();
        response.setCode(state.getCode());
        response.setMsg(state.getMsg());
        return response;
    }

    public static BaseResponse error(String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(ERROR);
        response.setMsg(message);
        return response;
    }
}
