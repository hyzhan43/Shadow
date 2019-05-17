package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangePasswordArgs extends ResetPasswordArgs{

    @NotBlank(message = "原密码不能为空")
    @ApiParam(value = "原密码", required = true)
    private String oldPassword;
}
