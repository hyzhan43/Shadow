package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class RefreshTokenArgs {

    @NotBlank(message = "refreshToken不能为空")
    @ApiParam(value = "刷新令牌", required = true)
    private String refreshToken;
}
