package com.example.core.bean.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/28
 * desc：    TODO
 */
@Data
public class ResponseCard<T> {
    @JsonProperty("error_code")
    private Integer errorCode;

    private T msg;

    private String request;
}
