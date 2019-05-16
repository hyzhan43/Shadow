package com.zhan.app.controller.cms;

import com.zhan.core.annotation.AdminRequired;
import com.zhan.core.annotation.Logger;
import com.zhan.core.annotation.RouteMeta;
import com.zhan.core.bean.BaseResponse;
import com.zhan.core.bean.Response;
import com.zhan.core.bean.args.*;
import com.zhan.core.bean.card.*;
import com.zhan.core.controller.BaseController;
import com.zhan.core.resource.AdminResource;
import com.zhan.core.utils.RouteMetaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * author：  HyZhan
 * create：  2019/4/6
 * desc：    TODO
 */

@Api(tags = "管理员模块")
@RestController
@RequestMapping("/cms/admin")
public class AdminController extends BaseController {

    private AdminResource adminResource;

    @Autowired
    public AdminController(AdminResource adminResource) {
        this.adminResource = adminResource;
    }

    @AdminRequired
    @GetMapping("/authority")
    @ApiOperation("查询所有可分配的权限")
    @RouteMeta(auth = "查询所有可分配的权限", module = "管理员", mount = false)
    public BaseResponse<Collection<RouteMetaCard>> authority() {
        Collection<RouteMetaCard> routeMetaCards = RouteMetaUtil.getRouteMetaCardMap().values();
        return Response.success(routeMetaCards);
    }

    @AdminRequired
    @GetMapping("/users")
    @ApiOperation("查询所有用户")
    @RouteMeta(auth = "查询所有用户", module = "管理员", mount = false)
    public BaseResponse<PageCard<AdminUserCard>> getAdminUsers(AdminUserArgs args) {
        // 校验页码
        checkPaginate(args);

        PageCard<AdminUserCard> pageCard = adminResource.getAdminUsers(args);

        return Response.success(pageCard);
    }

    @AdminRequired
    @PutMapping("/password/{id}")
    @ApiOperation("修改用户密码")
    @RouteMeta(auth = "修改用户密码", module = "管理员", mount = false)
    public BaseResponse ChangePassword(@ApiParam(value = "用户id", required = true)
                                       @PathVariable Integer id,
                                       @Valid ResetPasswordArgs args) {

        adminResource.changePassword(id, args);

        return Response.success("密码修改成功");
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    @Logger(template = "管理员删除了一个用户")
    @RouteMeta(auth = "删除用户", module = "管理员", mount = false)
    public BaseResponse deleteUser(@PathVariable Integer id) {
        adminResource.deleteUser(id);
        return Response.success("操作成功");
    }


    @AdminRequired
    @PutMapping("/{id}")
    @ApiOperation("更新用户")
    @RouteMeta(auth = "管理员更新用户信息", module = "管理员", mount = false)
    public BaseResponse updateUser(@ApiParam(value = "用户id", required = true)
                                   @PathVariable Integer id,
                                   @Valid UpdateUserArgs args) {

        adminResource.updateUser(id, args);

        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/disable/{id}")
    @ApiOperation("禁用用户")
    @RouteMeta(auth = "禁用用户", module = "管理员", mount = false)
    public BaseResponse transDisable(@ApiParam(value = "用户id", required = true)
                                     @PathVariable Integer id) {

        adminResource.transDisable(id);
        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/active/{id}")
    @ApiOperation("激活用户")
    @RouteMeta(auth = "激活用户", module = "管理员", mount = false)
    public BaseResponse transActive(@ApiParam(value = "用户id", required = true)
                                    @PathVariable Integer id) {

        adminResource.transActive(id);
        return Response.success("操作成功");
    }


    @AdminRequired
    @GetMapping("/groups")
    @ApiOperation("查询所有权限组及其权限")
    @RouteMeta(auth = "查询所有权限组及其权限", module = "管理员", mount = false)
    public BaseResponse<PageCard<GroupInfoCard>> getAdminGroups(PageArgs args) {

        checkPaginate(args);

        PageCard<GroupInfoCard> groupInfoCards = adminResource.getAdminGroups(args);

        return Response.success(groupInfoCards);
    }

    @AdminRequired
    @GetMapping("/group/all")
    @ApiOperation("查询所有权限组")
    @RouteMeta(auth = "查询所有权限组", module = "管理员", mount = false)
    public BaseResponse<PageCard<GroupCard>> getAllGroups(PageArgs args) {

        checkPaginate(args);

        PageCard<GroupCard> groupCards = adminResource.getAllGroups(args);

        return Response.success(groupCards);
    }

    @AdminRequired
    @GetMapping("/group/{id}")
    @ApiOperation("查询一个权限组及其权限")
    @RouteMeta(auth = "查询一个权限组及其权限", module = "管理员", mount = false)
    public BaseResponse<GroupInfoCard> getGroup(@ApiParam(value = "组id", required = true)
                                                @PathVariable Integer id) {

        GroupInfoCard groupInfoCard = adminResource.getGroup(id);

        return Response.success(groupInfoCard);
    }


    @AdminRequired
    @PostMapping("/group")
    @ApiOperation("新建权限组")
    @Logger(template = "管理员新建了一个权限组")
    @RouteMeta(auth = "新建权限组", module = "管理员", mount = false)
    public BaseResponse createGroup(@Valid NewGroupArgs args) {

        adminResource.createGroup(args);

        return Response.success("新建分组成功");
    }

    @AdminRequired
    @PutMapping("/group/{id}")
    @ApiOperation("更新一个权限组")
    @RouteMeta(auth = "更新一个权限组", module = "管理员", mount = false)
    public BaseResponse updateGroup(@ApiParam(value = "组id", required = true)
                                    @PathVariable Integer id,
                                    @Valid UpdateGroupArgs args) {

        adminResource.updateGroup(id, args);

        return Response.success("更新分组成功");
    }

    @AdminRequired
    @DeleteMapping("/group/{id}")
    @ApiOperation("删除一个权限组")
    @Logger(template = "管理员删除一个权限组")
    @RouteMeta(auth = "删除一个权限组", module = "管理员", mount = false)
    public BaseResponse deleteGroup(@ApiParam(value = "组id", required = true)
                                    @PathVariable Integer id) {

        adminResource.deleteGroup(id);

        return Response.success("删除分组成功");
    }


    @AdminRequired
    @PostMapping("/dispatch")
    @ApiOperation("分配单个权限")
    @RouteMeta(auth = "分配单个权限", module = "管理员", mount = false)
    public BaseResponse dispatchAuth(@Valid DispatchAuthArgs args) {

        adminResource.dispatchAuth(args);

        return Response.success("添加权限成功");
    }

    @AdminRequired
    @PostMapping("/dispatch/patch")
    @ApiOperation("分配多个权限")
    @RouteMeta(auth = "分配多个权限", module = "管理员", mount = false)
    public BaseResponse dispatchAuths(@Valid DispatchAuthsArgs args) {

        adminResource.dispatchAuths(args);

        return Response.success("添加权限成功");
    }

    @AdminRequired
    @PostMapping("/remove")
    @ApiOperation("删除多个权限")
    @RouteMeta(auth = "删除多个权限", module = "管理员", mount = false)
    public BaseResponse removeAuths(@Valid RemoveAuthsArgs args) {

        adminResource.removeAuths(args);

        return Response.success("删除权限成功");
    }
}
