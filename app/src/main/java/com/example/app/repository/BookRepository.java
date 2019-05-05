package com.example.app.repository;

import com.example.app.bean.db.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleLike(String title);

    Optional<Book> findByTitleAndDeleteTimeIsNull(String title);
}
