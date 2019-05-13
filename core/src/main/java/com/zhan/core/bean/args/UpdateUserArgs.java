package com.zhan.core.bean.args;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * author：  HyZhan
 * create：  2019/4/18
 * desc：    TODO
 */
@Data
public class UpdateUserArgs {

    @NotNull(message = "分组id不能为空")
    @Min(value = 0, message = "分组id必须大于0")
    private Integer groupId;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱不符合规范")
    private String email;
}
