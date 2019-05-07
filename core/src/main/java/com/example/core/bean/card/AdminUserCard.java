package com.example.core.bean.card;

import com.example.core.bean.db.User;
import com.example.core.utils.L;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

    @JsonProperty("group_id")
    private Integer groupId;

    @JsonProperty("group_name")
    private String groupName;

    private Integer admin;

    public AdminUserCard(User user) {
        this.groupName = user.getGroup().getName();
        BeanUtils.copyProperties(user, this);
    }
}
