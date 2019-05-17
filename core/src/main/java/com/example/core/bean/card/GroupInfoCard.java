package com.example.core.bean.card;

import com.example.core.bean.db.Group;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：  HyZhan
 * create：  2019/4/22
 * desc：    TODO
 */
@Data
public class GroupInfoCard {

    @ApiModelProperty("组id")
    private Integer id;
    // 组名
    @ApiModelProperty("分组名")
    private String name;
    // 组信息
    @ApiModelProperty("分组信息")
    private String info;

    @ApiModelProperty("分组下权限列表")
    private List<Map<String, List<AuthCard>>> auths;

    public GroupInfoCard(Group group, Map<String, List<AuthCard>> authMap) {
        this.id = group.getId();
        this.info = group.getInfo();
        this.name = group.getName();

        this.auths = new ArrayList<>();

        authMap.forEach((module, authCardList) -> {
            Map<String, List<AuthCard>> map = new HashMap<>();
            map.put(module, authCardList);
            this.auths.add(map);
        });
    }
}
