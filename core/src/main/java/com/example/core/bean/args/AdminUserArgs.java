package com.example.core.bean.args;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUserArgs extends PageArgs {

    public Integer group_id;
}