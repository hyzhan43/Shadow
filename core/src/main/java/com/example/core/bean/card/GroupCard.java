package com.example.core.bean.card;

import com.example.core.bean.db.Group;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/22
 * desc：    TODO
 */
@Data
public class GroupCard {

    private Integer id;
    // 组名
    private String name;
    // 组信息
    private String info;
    // 组权限
    private List<ModuleCard> modules;

    public GroupCard(Group group, List<ModuleCard> moduleCards) {
        BeanUtils.copyProperties(group, this);
        this.modules = moduleCards;
    }
}
