package com.zhan.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class NewGroupArgs {

    @NotBlank(message = "请输入分组名称")
    @ApiParam(value = "分组名称", required = true)
    private String name;

    @ApiParam(value = "分组信息")
    private String info;

    @NotNull(message = "请输入auths字段")
    @ApiParam(value = "分组权限", required = true)
    private List<String> auths;
}
