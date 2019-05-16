package com.zhan.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogArgs extends PageArgs{

    // name可选，若无则表示全部
    @ApiParam(value = "用户名")
    private String name;

    // 开始时间
    @ApiParam(value = "开始时间")
    private String startTime;

    // 结束时间
    @ApiParam(value = "结束时间")
    private String endTime;
}
