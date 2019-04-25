package com.example.core.bean.card;

import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class UserCard {
    private Integer id;

    private String nickname;

    /**
     * admin 代表是否超级管理员
     * 1 -> 普通用户
     * 2 -> 超级管理员
     */
    private Integer admin;

    /**
     * active 代表是否激活状态
     * 1 —> 激活
     * 2 -> 非激活
     */
    private Integer active;

    private String email;

    /**
     * 用户所属的权限组 id
     */
    private Integer groupId;

    private Group group;

    public UserCard(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
