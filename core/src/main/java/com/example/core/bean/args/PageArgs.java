package com.example.core.bean.args;

import com.example.core.config.Setting;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
public class PageArgs {

    @ApiParam("起始页")
    public Integer page = Setting.PAGE;

    @ApiParam("当前页总数")
    public Integer count = Setting.PAGE_SIZE;
}
