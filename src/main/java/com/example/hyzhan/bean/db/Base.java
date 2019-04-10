package com.example.hyzhan.bean.db;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/10
 * desc：    TODO
 */
@MappedSuperclass
class Base {

    @Column
    @CreatedDate
    private Date createTime;

    @Column
    @LastModifiedDate
    private Date updateTime;

    @Column
    @LastModifiedDate
    private Date deleteTime;
}
