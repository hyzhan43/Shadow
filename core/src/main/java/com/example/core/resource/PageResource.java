package com.example.core.resource;

import com.example.core.bean.card.PageCard;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public abstract class PageResource {

    protected <T> PageCard<T> convertPageCard(Page<T> source) {
        // 分页查询 默认从 0页开始 查询10条
        PageCard<T> pageCard = new PageCard<>();
        wrapPageData(source, pageCard);
        pageCard.setCollection(source.getContent());

        return pageCard;
    }

//    protected <T, R> PageCard<T> convertPageCard(Page<R> page, List<T> dataList) {
//        // 分页查询 默认从 0页开始 查询10条
//        PageCard<T> pageCard = new PageCard<>();
//        wrapPageData(page, pageCard);
//
//        pageCard.setCollection(dataList);
//        return pageCard;
//    }

    private <T, R> void wrapPageData(Page<R> page, PageCard<T> pageCard) {

        pageCard.setCurPage(page.getPageable().getPageNumber());
        pageCard.setSize(page.getPageable().getPageSize());
        pageCard.setPageCount(page.getTotalPages());
        pageCard.setTotal(page.getTotalElements());
    }
}
