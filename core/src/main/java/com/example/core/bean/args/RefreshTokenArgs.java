package com.example.core.bean.args;

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
    private String refreshToken;
}
