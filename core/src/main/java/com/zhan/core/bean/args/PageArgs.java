package com.zhan.core.bean.args;

import com.zhan.core.config.Setting;
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

    @ApiParam("一页数量")
    public Integer pageSize = Setting.PAGE_SIZE;
}
