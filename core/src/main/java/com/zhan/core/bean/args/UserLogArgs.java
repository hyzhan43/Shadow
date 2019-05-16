package com.zhan.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/25
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLogArgs extends LogArgs{

    @NotBlank(message = "搜索关键字不可为空")
    @ApiParam(value = "关键词", required = true)
    private String keyword;
}
