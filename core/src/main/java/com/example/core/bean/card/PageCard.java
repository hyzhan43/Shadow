package com.example.core.bean.card;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ApiModelProperty("数据")
    private List<T> collection;

    @JsonProperty("total_nums")
    @ApiModelProperty("总数")
    private long totalNums;
}
