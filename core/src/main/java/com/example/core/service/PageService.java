package com.example.core.service;

import com.example.core.bean.card.PageCard;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public abstract class PageService extends BaseService {

    protected <T> PageCard<T> convertPageCard(Page source, List<T> target) {
        // 分页查询 默认从 0页开始 查询10条
        PageCard<T> pageCard = new PageCard<>();

        pageCard.setCurPage(source.getPageable().getPageNumber());
        pageCard.setPageCount(source.getTotalPages());
        pageCard.setTotal(source.getTotalElements());
        pageCard.setCollection(target);

        return pageCard;
    }

    protected <T> PageCard<T> convertPageCard(Page<T> source) {
        // 分页查询 默认从 0页开始 查询10条
        PageCard<T> pageCard = new PageCard<>();

        pageCard.setCurPage(source.getPageable().getPageNumber());
        pageCard.setPageCount(source.getTotalPages());
        pageCard.setTotal(source.getTotalElements());
        pageCard.setCollection(source.getContent());

        return pageCard;
    }
}
