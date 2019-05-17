package com.example.core.bean.card;

import com.example.core.bean.db.Group;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * author：  HyZhan
 * create：  2019/4/23
 * desc：    TODO
 */
@Data
public class GroupCard {

    @ApiModelProperty("分组id")
    private Integer id;
    // 组名
    @ApiModelProperty("分组名")
    private String name;
    // 组信息
    @ApiModelProperty("分组信息")
    private String info;

    public GroupCard(Group group) {
        BeanUtils.copyProperties(group, this);
    }
}
