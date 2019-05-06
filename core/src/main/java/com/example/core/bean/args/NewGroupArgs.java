package com.example.core.bean.args;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class NewGroupArgs {

    @NotBlank(message = "请输入分组名称")
    private String name;

    private String info;

    private List<String> auths;
}
