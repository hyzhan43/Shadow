package com.example.core.bean.args;

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
    private String new_password;

    @NotBlank(message = "确认密码不能为空")
    private String confirm_password;
}
