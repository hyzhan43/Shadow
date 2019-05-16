package com.zhan.app.bean.card;

import com.zhan.app.bean.db.Book;
import com.zhan.core.bean.card.BaseCard;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class BookCard extends BaseCard {

    public static final String DEFAULT_AUTHOR = "未名";

    @ApiModelProperty(value = "图书id")
    private Integer id;

    @ApiModelProperty(value = "图书名称")
    private String title;

    @ApiModelProperty(value = "图书作者")
    private String author = DEFAULT_AUTHOR;

    @ApiModelProperty(value = "图书简介")
    private String summary;

    @ApiModelProperty(value = "图书插图")
    private String image;

    /**
     *  格式化输出
     */
    @ApiModelProperty(value = "图书创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public BookCard(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();

        if (!checkIsNullOrEmpty(book.getAuthor())){
            this.author = book.getAuthor();
        }

        this.summary = book.getSummary();
        this.image = book.getImage();

        this.createTime = book.getCreateTime();
    }
}
