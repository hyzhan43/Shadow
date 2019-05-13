package com.zhan.core.bean.args;

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
    private String oldPassword;
}
