package com.example.app.controller.cms;

import com.example.core.annotation.*;
import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.bean.args.*;
import com.example.core.bean.card.ResponseCard;
import com.example.core.bean.card.TokenCard;
import com.example.core.bean.card.UserApisCard;
import com.example.core.bean.card.UserCard;
import com.example.core.resource.UserResource;
import com.example.core.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("cms/user")
public class UserController {

    private UserResource userResource;

    @Autowired
    public UserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @AdminRequired
    @PostMapping("/register")
    @ApiOperation("注册用户")
    @Logger(template = "管理员新建了一个用户")
    @RouteMeta(auth = "注册", module = "用户", mount = false)
    public ResponseCard register(@Valid @RequestBody RegisterArgs args) {

        userResource.register(args);
        return Response.success("注册成功");
    }

    @PostMapping("/login")
    @ApiOperation("用户登陆")
    @RouteMeta(auth = "登陆", module = "用户", mount = false)
    public TokenCard login(@Valid @RequestBody LoginArgs args) {
        return userResource.login(args);
    }

    @LoginRequired
    @PutMapping("/")
    @ApiOperation("用户更新信息")
    @RouteMeta(auth = "用户更新信息", module = "用户", mount = false)
    public ResponseCard update(@Valid @RequestBody UpdateInfoArgs args) {

        String email = args.getEmail();
        if (email != null && !email.isEmpty()) {
            userResource.updateInfo(args);
        }

        return Response.success("操作成功");
    }

    @LoginRequired
    @PutMapping("/password/change")
    @ApiOperation("修改密码")
    @Logger(template = "{user.nickname}修改了自己的密码")
    @RouteMeta(auth = "修改密码", module = "用户", mount = false)
    public ResponseCard changePassword(@Valid @RequestBody ChangePasswordArgs args) {

        userResource.changePassword(args);

        return Response.success("密码修改成功");
    }

    @LoginRequired
    @GetMapping("/information")
    @ApiOperation("查询自己信息")
    @RouteMeta(auth = "查询自己信息", module = "用户", mount = false)
    public UserCard getInformation() {
        return userResource.getUserInfo();
    }

    @GetMapping("/refresh")
    @ApiOperation("刷新令牌")
    @RouteMeta(auth = "刷新令牌", module = "用户", mount = false)
    public TokenCard refreshToken(@Valid @RequestBody RefreshTokenArgs args) {
        return userResource.refreshToken(args);
    }


    @LoginRequired
    @GetMapping("/auths")
    @ApiOperation("查询自己拥有的权限")
    @RouteMeta(auth = "查询自己拥有的权限", module = "用户", mount = false)
    public UserApisCard getAllowedApis() {
        return userResource.getAllowedApis();
    }
}
