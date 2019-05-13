package com.zhan.core.bean.card;

import com.zhan.core.bean.db.User;
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
