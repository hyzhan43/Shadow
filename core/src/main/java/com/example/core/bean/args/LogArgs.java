package com.example.core.bean.args;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogArgs extends PageArgs{

    // name可选，若无则表示全部
    private String name;

    // 开始时间
    private String startTime;

    // 结束时间
    private String endTime;
}
