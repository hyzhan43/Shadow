package com.zhan.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Data
public class LoginArgs {

    @NotBlank(message = "昵称不能为空")
    @ApiParam(value = "昵称", required = true)
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @ApiParam(value = "密码", required = true)
    private String password;
}
