package com.example.app.resource;

import com.example.app.bean.args.BookSearchArgs;
import com.example.app.bean.args.CreateOrUpdateBookArgs;
import com.example.app.bean.card.BookCard;
import com.example.app.bean.db.Book;
import com.example.app.bean.model.BookModel;
import com.example.app.service.BookService;
import com.example.core.bean.card.PageCard;
import com.example.core.resource.PageResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
@Component
public class BookResource extends PageResource {

    private BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    public BookCard getBookById(Integer id) {
        Book book = bookService.getBookById(id);
        return new BookCard(book);
    }

    public PageCard<BookCard> searchBook(BookSearchArgs args) {

        Pageable pageable = PageRequest.of(args.getPage(), args.getPageSize());

        Page<BookCard> bookPage = bookService.searchBookByTitle(args.getKeyword(), pageable)
                .map(BookCard::new);

        return convertPageCard(bookPage);
    }

    public void createBook(CreateOrUpdateBookArgs args) {

        bookService.findBookByTitle(args.getTitle());

        BookModel model = new BookModel();
        BeanUtils.copyProperties(args, model);

        bookService.createBook(model);
    }

    public void updateBook(Integer id, CreateOrUpdateBookArgs args) {
        Book book = bookService.getBookById(id);

        BeanUtils.copyProperties(args, book);
        bookService.updateBook(book);
    }

    public void deleteBook(Integer id) {
        Book book = bookService.getBookById(id);
        bookService.deleteBook(book, false);
    }
}
