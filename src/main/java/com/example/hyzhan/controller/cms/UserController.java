package com.example.hyzhan.controller.cms;

import com.example.hyzhan.annotation.AdminRequired;
import com.example.hyzhan.annotation.GroupRequired;
import com.example.hyzhan.annotation.LoginRequired;
import com.example.hyzhan.annotation.RouteMeta;
import com.example.hyzhan.bean.BaseResponse;
import com.example.hyzhan.bean.Response;
import com.example.hyzhan.bean.args.LoginArgs;
import com.example.hyzhan.bean.args.RegisterArgs;
import com.example.hyzhan.bean.card.TokenCard;
import com.example.hyzhan.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

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

    @LoginRequired
    @GetMapping("/test")
    public String test(){
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
