package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
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
    @ApiParam(value = "昵称", required = true)
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @ApiParam(value = "密码", required = true)
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @ApiParam(value = "确认密码", required = true)
    private String confirm_password;

    @NotNull(message = "分组id不能为空")
    @Min(value = 0, message = "分组id必须大于0")
    @ApiParam(value = "分组id", required = true)
    private Integer group_id;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱不符合规范")
    @ApiParam(value = "邮箱", required = true)
    private String email;
}
