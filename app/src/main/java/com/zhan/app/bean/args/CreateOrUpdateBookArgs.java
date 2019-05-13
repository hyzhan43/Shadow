package com.zhan.app.bean.args;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/27
 * desc：    TODO
 */
@Data
public class CreateOrUpdateBookArgs {

    @NotBlank(message = "必须传入图书名")
    private String title;

    @NotBlank(message = "必须传入图书作者")
    private String author;

    @NotBlank(message = "必须传入图书综述")
    private String summary;

    @NotBlank(message = "必须传入图书插图")
    private String image;
}
