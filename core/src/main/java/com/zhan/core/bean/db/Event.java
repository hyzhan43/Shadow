package com.zhan.core.bean.db;

import lombok.Data;

import javax.persistence.*;

/**
 * author：  HyZhan
 * create：  2019/4/10
 * desc：    TODO
 */
@Entity
@Data
@Table(name = "lin_event")
public class Event {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer groupId;

    @Column
    private String messageEvents;
}
