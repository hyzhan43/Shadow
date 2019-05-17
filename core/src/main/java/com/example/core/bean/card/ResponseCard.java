package com.example.core.bean.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/28
 * desc：    TODO
 */
@Data
@ApiModel
public class ResponseCard<T> {
    @JsonProperty("error_code")
    @ApiModelProperty("错误码")
    private Integer errorCode;

    @ApiModelProperty("请求结果信息")
    private T msg;

    @ApiModelProperty("请求路径")
    private String request;
}
