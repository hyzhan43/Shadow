package com.example.app.bean.card;

import com.example.app.bean.db.Book;
import com.example.core.bean.card.BaseCard;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookCard extends BaseCard {

    public static final String DEFAULT_AUTHOR = "未名";

    private Integer id;

    private String title;

    // 默认值
    private String author = DEFAULT_AUTHOR;

    private String summary;

    private String image;

    /**
     *  格式化输出
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    public BookCard(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();

        if (!checkIsNullOrEmpty(book.getAuthor())){
            this.author = book.getAuthor();
        }

        this.summary = book.getSummary();
        this.image = book.getImage();

        this.create_time = book.getCreateTime();
    }
}
