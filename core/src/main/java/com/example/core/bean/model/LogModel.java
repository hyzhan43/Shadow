package com.example.core.bean.model;

import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/25
 * desc：    TODO
 */
@Data
public class LogModel {

    private Integer userId;

    private String username;

    private Integer statusCode;

    private String template;

    private String method;

    private String methodName;

    private String path;
}
