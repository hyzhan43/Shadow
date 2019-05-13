package com.zhan.core.bean.db;

import lombok.Data;

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

    // 普通用户
    public static final int COMMON = 1;
    // 超级管理员
    public static final int SUPER = 2;

    public static final int ACTIVE = 1;
    public static final int FORBID = 2;


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
    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer admin = 1;

    /**
     * active 代表是否激活状态
     * 1 —> 激活
     * 2 -> 非激活
     */
    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer active = 1;

    @Column(length = 100, unique = true)
    private String email;

    /**
     * 用户所属的权限组 id
     */
    @Column(insertable = false, updatable = false)
    private Integer groupId;

    @OneToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @Column(length = 100)
    private String password;

    public boolean isSuper(){
        return this.admin == SUPER;
    }

    public boolean isForbid(){
        return this.active == FORBID;
    }
}
