package com.example.app.controller.cms;

import com.example.core.annotation.*;
import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.bean.args.LoginArgs;
import com.example.core.bean.args.RegisterArgs;
import com.example.core.bean.card.TokenCard;
import com.example.core.resource.UserResource;
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

    private UserResource userResource;

    @Autowired
    public UserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @PostMapping("/register")
    @RouteMeta(auth = "注册", module = "用户", mount = false)
    public BaseResponse register(@Valid @RequestBody RegisterArgs args) {

        userResource.register(args);
        return Response.success("注册成功");
    }

    @PostMapping("/login")
    @RouteMeta(auth = "登陆", module = "用户", mount = false)
    public BaseResponse login(@Valid @RequestBody LoginArgs args) {

        TokenCard tokenCard = userResource.login(args);
        return Response.success(tokenCard);
    }
}
