package com.example.core.bean.args;

import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/25
 * desc：    TODO
 */
@Data
public class LogInfoArgs {

    private Integer userId;

    private Integer statusCode;

    private String template;

    private String method;

    private String methodName;

    private String path;
}
