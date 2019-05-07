package com.example.core.bean.card;

import com.example.core.bean.db.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<Map<String, List<AuthCard>>> auths;

    public UserApisCard(User user, Map<String, List<AuthCard>> authMap) {
        BeanUtils.copyProperties(user, this);

        this.auths = new ArrayList<>();
        authMap.forEach((module, authCardList) -> {
            Map<String, List<AuthCard>> map = new HashMap<>();
            map.put(module, authCardList);
            this.auths.add(map);
        });
    }
}
