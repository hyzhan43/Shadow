package com.zhan.app.controller.cms;

import com.zhan.core.annotation.Logger;
import com.zhan.core.annotation.LoginRequired;
import com.zhan.core.annotation.RouteMeta;
import com.zhan.core.bean.BaseResponse;
import com.zhan.core.bean.Response;
import com.zhan.core.bean.args.*;
import com.zhan.core.bean.card.TokenCard;
import com.zhan.core.bean.card.UserApisCard;
import com.zhan.core.bean.card.UserCard;
import com.zhan.core.resource.UserResource;
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
    public BaseResponse register(@Valid RegisterArgs args) {

        userResource.register(args);
        return Response.success("注册成功");
    }

    @PostMapping("/login")
    @RouteMeta(auth = "登陆", module = "用户", mount = false)
    public BaseResponse login(@Valid LoginArgs args) {

        TokenCard tokenCard = userResource.login(args);
        return Response.success(tokenCard);
    }

    @LoginRequired
    @PutMapping("")
    @RouteMeta(auth = "用户更新信息", module = "用户", mount = false)
    public BaseResponse update(@Valid UpdateInfoArgs args) {

        String email = args.getEmail();
        if (email != null && !email.isEmpty()) {
            userResource.updateInfo(args);
        }

        return Response.success("操作成功");
    }

    @LoginRequired
    @PutMapping("/password/change")
    @Logger(template = "{user.nickname}修改了自己的密码")
    @RouteMeta(auth = "修改密码", module = "用户", mount = false)
    public BaseResponse changePassword(@Valid ChangePasswordArgs args) {

        userResource.changePassword(args);

        return Response.success("密码修改成功");
    }

    @LoginRequired
    @GetMapping("/information")
    @RouteMeta(auth = "查询自己信息", module = "用户", mount = false)
    public BaseResponse getInformation() {

        UserCard userCard = userResource.getUserInfo();
        return Response.success(userCard);
    }

    @GetMapping("/refresh")
    @RouteMeta(auth = "刷新令牌", module = "用户", mount = false)
    public BaseResponse refreshToken(@Valid RefreshTokenArgs args) {
        TokenCard tokenCard = userResource.refreshToken(args);
        return Response.success(tokenCard);
    }


    @LoginRequired
    @GetMapping("/auths")
    @RouteMeta(auth = "查询自己拥有的权限", module = "用户", mount = false)
    public BaseResponse getAllowedApis() {
        UserApisCard userApisCard = userResource.getAllowedApis();
        return Response.success(userApisCard);
    }
}
