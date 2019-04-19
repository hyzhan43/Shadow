package com.example.core.bean;

import lombok.Data;

/**
 * author：  HyZhan
 * create：  2018/11/6 20:33
 * desc：    http 请求返回 基类
 */
@Data
public class BaseResponse<T> {

    private Integer code;

    private String msg;

    private T data;
}
