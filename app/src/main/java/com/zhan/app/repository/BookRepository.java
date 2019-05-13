package com.zhan.app.repository;

import com.zhan.app.bean.db.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByTitleLike(String title, Pageable pageable);

    Optional<Book> findByTitleAndDeleteTimeIsNull(String title);
}
