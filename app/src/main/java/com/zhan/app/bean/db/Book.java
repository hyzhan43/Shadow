package com.zhan.app.bean.db;

import com.zhan.core.bean.db.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Data
@Entity
@Table(name = "book")
@EqualsAndHashCode(callSuper = true)
public class Book extends Base{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 30, nullable = false)
    private String author;

    @Column(length = 1000)
    private String summary;

    @Column(length = 50)
    private String image;
}
