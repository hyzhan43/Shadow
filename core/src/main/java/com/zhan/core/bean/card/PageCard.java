package com.zhan.core.bean.card;

import com.zhan.core.config.Setting;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
@ApiModel
public class PageCard<T> {

    // 当前页
    @ApiModelProperty(value = "当前页")
    private Integer curPage;
    // 页数
    @ApiModelProperty(value = "总页数")
    private Integer pageCount;
    // 当前页  条数
    @ApiModelProperty(value = "一页数量")
    private Integer size = Setting.PAGE_SIZE;
    // 总共
    @ApiModelProperty(value = "总个数")
    private long total;
    // 数据
    @ApiModelProperty(value = "数据")
    private List<T> collection;
}
