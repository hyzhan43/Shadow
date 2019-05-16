package com.zhan.app.bean.args;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/27
 * desc：    TODO
 */
@Data
public class CreateOrUpdateBookArgs {

    @NotBlank(message = "必须传入图书名称")
    @ApiParam(value = "图书名称", required = true)
    private String title;

    @NotBlank(message = "必须传入图书作者")
    @ApiParam(value = "图书作者", required = true)
    private String author;

    @NotBlank(message = "必须传入图书综述")
    @ApiParam(value = "图书综述", required = true)
    private String summary;

    @NotBlank(message = "必须传入图书插图")
    @ApiParam(value = "图书插图", required = true)
    private String image;
}
