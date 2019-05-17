package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class DispatchAuthArgs {

    @NotNull(message = "请输入分组id")
    @Min(value = 1, message = "分组id必须大于0")
    @ApiParam(value = "组id", required = true)
    private Integer group_id;

    @NotBlank(message = "请输入auth字段")
    @ApiParam(value = "分组权限", required = true)
    private String auth;
}
