package com.example.core.bean.db;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/10
 * desc：    TODO
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
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
