package com.example.core.bean.card;

import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/28
 * desc：    TODO
 */
@Data
public class ResponseCard<T> {
    private Integer error_code;

    private T msg;

    private String request;
}
