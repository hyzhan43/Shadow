package com.example.core.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class UpdateInfoArgs {

    @Email
    @ApiParam(value = "邮箱")
    private String email;
}
