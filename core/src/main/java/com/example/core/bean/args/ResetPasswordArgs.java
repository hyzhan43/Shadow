package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/18
 * desc：    TODO
 */
@Data
public class ResetPasswordArgs {

    @NotBlank(message = "新密码不能为空")
    @ApiParam(value = "新密码", required = true)
    private String new_password;

    @NotBlank(message = "确认密码不能为空")
    @ApiParam(value = "确认密码", required = true)
    private String confirm_password;
}
