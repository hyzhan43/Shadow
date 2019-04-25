package com.example.core.repository;

import com.example.core.bean.db.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findByName(String name);
}
