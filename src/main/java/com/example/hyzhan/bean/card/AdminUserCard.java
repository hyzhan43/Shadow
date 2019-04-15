package com.example.hyzhan.bean.card;

import com.example.hyzhan.bean.db.cms.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
public class AdminUserCard {

    private Integer id;

    private String nickname;

    private Integer active;

    private String email;

    private Integer groupId;

    private String groupName;

    private Integer admin;

    public AdminUserCard(User user) {
        this.groupName = user.getGroup().getName();
        BeanUtils.copyProperties(user, this);
    }
}
