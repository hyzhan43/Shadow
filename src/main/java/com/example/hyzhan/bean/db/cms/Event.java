package com.example.hyzhan.bean.db.cms;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Integer id;

    @Column(nullable = false)
    private Integer groupId;

    @Column
    private String messageEvents;
}
