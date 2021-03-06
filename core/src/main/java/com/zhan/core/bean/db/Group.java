package com.zhan.core.bean.db;

import lombok.Data;

import javax.persistence.*;

/**
 * author：  HyZhan
 * create：  2019/4/10
 * desc：    group 表
 */
@Entity
@Data
@Table(name = "lin_group")
public class Group {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     *  权限组名称
     */
    @Column(length = 60)
    private String name;

    /**
     *  权限组描述
     */
    @Column
    private String info;
}
