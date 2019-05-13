package com.zhan.core.bean.args;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/25
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLogArgs extends LogArgs{

    @NotBlank(message = "搜索关键字不可为空")
    private String keyword;
}
