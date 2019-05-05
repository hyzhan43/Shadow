package com.example.core.bean.card;

import com.example.core.config.Setting;
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

    private long total_nums;
}
