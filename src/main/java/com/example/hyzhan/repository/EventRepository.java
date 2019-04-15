package com.example.hyzhan.repository;

import com.example.hyzhan.bean.db.cms.Auth;
import com.example.hyzhan.bean.db.cms.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
