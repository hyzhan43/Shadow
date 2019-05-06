package com.example.core.bean.args;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class DispatchAuthsArgs {

    @NotNull(message = "请输入分组id")
    @Min(value = 1, message = "分组id必须大于0")
    private Integer group_id;

    @NotNull(message = "请输入auths字段")
    private List<String> auths;
}
