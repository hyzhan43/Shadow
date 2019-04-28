package com.example.core.bean.db;

import lombok.Data;

import javax.persistence.*;

/**
 * author：  HyZhan
 * create：  2019/4/10
 * desc：    auth 表
 */
@Entity
@Data
@Table(name = "lin_auth")
public class Auth {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     *  所属权限组 id
     */
    @Column(nullable = false)
    private Integer groupId;

    /**
     *  权限字段
     */
    @Column(length = 60)
    private String auth;

    /**
     *  权限模块
     */
    @Column(length = 50)
    private String module;
}
