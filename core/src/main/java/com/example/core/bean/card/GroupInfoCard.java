package com.example.core.bean.card;

import com.example.core.bean.db.Group;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * author：  HyZhan
 * create：  2019/4/22
 * desc：    TODO
 */
@Data
public class GroupInfoCard {

    private Integer id;
    // 组名
    private String name;
    // 组信息
    private String info;

    private Object[] auths;

    public GroupInfoCard(Group group, Map<String, List<AuthCard>> authMap) {
        this.id = group.getId();
        this.info = group.getInfo();
        this.name = group.getName();
        this.auths = new Object[]{authMap};
    }
}
