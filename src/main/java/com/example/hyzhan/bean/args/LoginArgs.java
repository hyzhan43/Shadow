package com.example.hyzhan.bean.args;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Data
public class LoginArgs {

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    private String password;
}
