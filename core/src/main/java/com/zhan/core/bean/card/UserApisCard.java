package com.zhan.core.bean.card;

import com.zhan.core.bean.db.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class UserApisCard {

    private Integer id;

    private String nickname;

    private Integer admin;

    private Integer active;

    private String email;

    private Integer groupId;

    private List<AuthCard> auths;

    public UserApisCard(User user, List<AuthCard> auths) {
        BeanUtils.copyProperties(user, this);
        this.auths = auths;
    }
}
