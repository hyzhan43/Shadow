package com.example.hyzhan.repository;

import com.example.hyzhan.bean.db.cms.Auth;
import com.example.hyzhan.bean.db.cms.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public interface LogRepository extends JpaRepository<Log, Integer> {
}
