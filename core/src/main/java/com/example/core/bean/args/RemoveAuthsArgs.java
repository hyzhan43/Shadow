package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class RemoveAuthsArgs {

    @NotNull(message = "请输入分组id")
    @Min(value = 1, message = "分组id必须大于0")
    @ApiParam(value = "组id", required = true)
    private Integer group_id;

    @NotNull(message = "请输入auths字段")
    @ApiParam(value = "分组权限列表", required = true)
    private List<String> auths;
}
