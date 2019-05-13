package com.zhan.app.controller.v1;

import com.zhan.app.bean.args.BookSearchArgs;
import com.zhan.app.bean.args.CreateOrUpdateBookArgs;
import com.zhan.app.bean.card.BookCard;
import com.zhan.app.resource.BookResource;
import com.zhan.core.annotation.GroupRequired;
import com.zhan.core.bean.BaseResponse;
import com.zhan.core.bean.Response;
import com.zhan.core.bean.card.PageCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    通过 图书 来实现一套标准的 CRUD 功能，供学习
 */
@RestController
@RequestMapping("/v1/book")
public class BookController {

    private BookResource bookResource;

    @Autowired
    public BookController(BookResource bookResource) {
        this.bookResource = bookResource;
    }

    @GetMapping("/{id}")
    public BaseResponse getBook(@PathVariable Integer id) {
        BookCard bookCard = bookResource.getBookById(id);
        return Response.success(bookCard);
    }

    @GetMapping("/search")
    public BaseResponse searchBook(@Valid BookSearchArgs args) {

        PageCard<BookCard> bookPageCard = bookResource.searchBook(args);

        return Response.success(bookPageCard);
    }

    @PostMapping("")
    public BaseResponse createBook(@Valid CreateOrUpdateBookArgs args) {

        bookResource.createBook(args);

        return Response.success("新建图书成功");
    }

    @PutMapping("/{id}")
    public BaseResponse updateBook(@PathVariable Integer id, @Valid CreateOrUpdateBookArgs args) {

        bookResource.updateBook(id, args);
        return Response.success("更新图书成功");
    }


    @GroupRequired
    @DeleteMapping("/{id}")
    public BaseResponse updateBook(@PathVariable Integer id) {

        bookResource.deleteBook(id);
        return Response.success("删除图书成功");
    }
}
