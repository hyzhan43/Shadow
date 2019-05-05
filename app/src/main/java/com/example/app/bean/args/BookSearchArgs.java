package com.example.app.bean.args;

import com.example.core.bean.args.PageArgs;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Data
public class BookSearchArgs{

    @NotBlank(message = "必须传入搜索关键字")
    private String q;
}
