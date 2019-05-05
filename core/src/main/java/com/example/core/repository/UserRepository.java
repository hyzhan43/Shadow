package com.example.core.repository;

import com.example.core.bean.db.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * "if(?1 !='',admin=?1,1=1)"
     * 如果 ?1 (第一个参数admin) 不为空 就将参数传入查询条件, 为空时就 1=1 为真, 不加入查询条件(对查询结果不产生作用)
     */
    @Query(value = "select * from lin_user where if(?1 !='',admin=?1,1=1) and " +
            "if(?2 !='',group_id=?2,1=1)", nativeQuery = true)
    Page<User> getUserByAdminAndGroupId(Integer admin, Integer groupId, Pageable pageable);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);

    Optional<User> findByGroupId(Integer groupId);
}
