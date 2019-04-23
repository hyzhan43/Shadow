package com.example.core.bean.args;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUserArgs extends BaseArgs{

    public Integer groupId;
}
