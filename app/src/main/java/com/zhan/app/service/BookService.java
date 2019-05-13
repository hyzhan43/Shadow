package com.zhan.app.service;

import com.zhan.app.bean.db.Book;
import com.zhan.app.bean.model.BookModel;
import com.zhan.app.error.code.APICode;
import com.zhan.app.error.APIException;
import com.zhan.app.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book getBookById(Integer id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (!bookOptional.isPresent()){
           throw new APIException(APICode.BOOK_NOT_FOUND);
        }

        return bookOptional.get();
    }

    public Page<Book> searchBookByTitle(String keyword, Pageable pageable) {
        return bookRepository.findByTitleLike(keyword, pageable);
    }

    public void findBookByTitle(String title) {

        // 避免同名
        Optional<Book> bookOptional = bookRepository.findByTitleAndDeleteTimeIsNull(title);

        if (bookOptional.isPresent()){
            throw new APIException(APICode.BOOK_IS_EXIST);
        }
    }

    public void createBook(BookModel model) {
        Book book = new Book();

        BeanUtils.copyProperties(model, book);

        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    // 删除图书，软删除
    public void deleteBook(Book book, boolean delete) {

        if (delete){
            // 删除图书，硬删除
            bookRepository.save(book);
            return;
        }

        // 软删除
        book.setDeleteTime(new Date());
        bookRepository.save(book);
    }
}
