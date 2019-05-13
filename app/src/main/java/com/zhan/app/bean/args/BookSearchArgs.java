package com.zhan.app.bean.args;

import com.zhan.core.bean.args.PageArgs;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookSearchArgs extends PageArgs {

    @NotBlank(message = "必须传入搜索关键字")
    private String keyword;
}