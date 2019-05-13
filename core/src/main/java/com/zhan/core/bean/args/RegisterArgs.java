package com.zhan.core.bean.args;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "分组id不能为空")
    @Min(value = 0, message = "分组id必须大于0")
    private Integer groupId;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱不符合规范")
    private String email;
}
