package com.example.app.bean.args;

import com.example.core.bean.args.PageArgs;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Data
public class BookSearchArgs extends PageArgs {

    @NotBlank(message = "必须传入搜索关键字")
    private String keyword;
}
