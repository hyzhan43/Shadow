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

    // 当前页
    private Integer curPage;
    // 页数
    private Integer pageCount;
    // 当前页  条数
    private Integer size = Setting.PAGE_SIZE;
    // 总共
    private long total;
    // 数据
    private List<T> collection;


}
