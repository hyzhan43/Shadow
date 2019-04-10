package com.example.hyzhan.bean.db.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * author：  HyZhan
 * create：  2019/4/10
 * desc：    user 表
 */
@Entity
@Data
@Table(name = "lin_user")
public class User{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 24, nullable = false, unique = true)
    private String nickname;

    /**
     * admin 代表是否超级管理员
     * 1 -> 普通用户
     * 2 -> 超级管理员
     */
    @Column(nullable = false)
    private Integer admin;

    /**
     * active 代表是否激活状态
     * 1 —> 激活
     * 2 -> 非激活
     */
    @Column(nullable = false)
    private Integer active;

    @Column(length = 100, unique = true)
    private String email;

    /**
     * 用户所属的权限组 id
     */
    @Column
    private Integer groudId;

    @Column(length = 100)
    private String password;
}
