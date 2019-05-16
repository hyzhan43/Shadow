package com.zhan.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class DispatchAuthsArgs {

    @NotNull(message = "请输入分组id")
    @Min(value = 1, message = "分组id必须大于0")
    @ApiParam(value = "分组id必须大于0", required = true)
    private Integer groupId;

    @NotNull(message = "请输入auths字段")
    @ApiParam(value = "权限列表", required = true)
    private List<String> auths;
}
