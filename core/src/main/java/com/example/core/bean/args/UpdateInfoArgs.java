package com.example.core.bean.args;

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
    private String email;
}
