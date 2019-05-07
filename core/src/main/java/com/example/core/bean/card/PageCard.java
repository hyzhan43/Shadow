package com.example.core.bean.card;

import com.example.core.config.Setting;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
public class PageCard<T> {

    private List<T> collection;

    @JsonProperty("total_nums")
    private long totalNums;
}
