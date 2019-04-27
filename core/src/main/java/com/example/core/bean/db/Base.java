package com.example.core.bean.db;

import lombok.Data;
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
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @Column
    @CreatedDate
    private Date createTime;

    @Column
    @LastModifiedDate
    private Date updateTime;

    @Column
    private Date deleteTime;
}
