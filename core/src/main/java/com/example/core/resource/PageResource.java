package com.example.core.resource;

import com.example.core.bean.card.PageCard;
import org.springframework.data.domain.Page;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public abstract class PageResource {

    protected <T> PageCard<T> convertPageCard(Page<T> source) {
        // 分页查询 默认从 0页开始 查询10条
        PageCard<T> pageCard = new PageCard<>();

        pageCard.setTotalNums(source.getTotalElements());
        pageCard.setCollection(source.getContent());

        return pageCard;
    }
}
