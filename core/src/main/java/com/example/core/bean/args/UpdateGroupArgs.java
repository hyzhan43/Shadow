package com.example.core.bean.args;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class UpdateGroupArgs {
    @NotBlank(message = "请输入分组名称")
    public String name;

    public String info;
}
