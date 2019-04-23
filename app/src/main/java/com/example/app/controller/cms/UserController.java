package com.example.app.controller.cms;

import com.example.core.annotation.*;
import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.bean.args.LoginArgs;
import com.example.core.bean.args.RegisterArgs;
import com.example.core.bean.card.TokenCard;
import com.example.core.service.user.UserService;
import com.example.core.utils.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@RestController
@RequestMapping("cms/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @RouteMeta(auth = "注册", module = "用户", mount = false)
    public BaseResponse register(@Valid @RequestBody RegisterArgs args) {

        userService.register(args);
        return Response.success("注册成功");
    }

    @PostMapping("/login")
    @RouteMeta(auth = "登陆", module = "用户", mount = false)
    public BaseResponse login(@Valid @RequestBody LoginArgs args) {

        TokenCard tokenCard = userService.login(args);
        return Response.success(tokenCard);
    }

    @Logger(template = "hello")
    @GetMapping("/test")
    @LoginRequired
    public String test(){
        L.error("111");
        return "test";
    }

    @AdminRequired
    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }

    @GroupRequired
    @GetMapping("/test3")
    public String test3(){
        return "test3";
    }
}
