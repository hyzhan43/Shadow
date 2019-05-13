package com.zhan.core.bean.card;

import com.zhan.core.bean.db.Group;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * author：  HyZhan
 * create：  2019/4/23
 * desc：    TODO
 */
@Data
public class GroupCard {

    private Integer id;
    // 组名
    private String name;
    // 组信息
    private String info;

    public GroupCard(Group group) {
        BeanUtils.copyProperties(group, this);
    }
}
