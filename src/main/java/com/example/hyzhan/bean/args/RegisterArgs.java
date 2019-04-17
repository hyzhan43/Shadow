package com.example.hyzhan.bean.args;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Data
public class RegisterArgs {

    @NotBlank(message = "昵称不可为空")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @Min(value = 0, message = "分组id必须大于0")
    private Integer groupId;

    @Email(message = "电子邮箱不符合规范")
    private String email;
}
