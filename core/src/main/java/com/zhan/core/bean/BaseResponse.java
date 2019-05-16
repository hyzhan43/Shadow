package com.zhan.core.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2018/11/6 20:33
 * desc：    http 请求返回 基类
 */
@Data
@ApiModel
public class BaseResponse<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "信息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;
}
