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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * author：  HyZhan
 * create：  2019/4/6
 * desc：    TODO
 */

@RestController
@RequestMapping("/cms/admin")
public class AdminController extends BaseController {

    private AdminResource adminResource;

    @Autowired
    public AdminController(AdminResource adminResource) {
        this.adminResource = adminResource;
    }

    /**
     * @api {get} /admin/authority 获取所有可分配权限
     * @apiGroup admin
     * @apiVersion 1.0.0
     * @apiSuccessExample {json} 返回样例：
     * {"code":0,"msg":"获取成功","data":[{"auth":"查询所有用户","module":"管理员","mount":false},{"auth":"删除用户","module":"管理员","mount":false},{"auth":"修改用户密码","module":"管理员","mount":false}]}
     */
    @AdminRequired
    @GetMapping("/authority")
    @RouteMeta(auth = "查询所有可分配的权限", module = "管理员", mount = false)
    public BaseResponse authority() {
        Collection<RouteMetaCard> routeMetaCards = RouteMetaUtil.getRouteMetaCardMap().values();
        return Response.success(routeMetaCards);
    }

    /**
     * @api {get} /admin/users 获取所有用户
     * @apiGroup admin
     * @apiVersion 1.0.0
     * @apiSuccessExample {json} 返回样例：
     * {"code":0,"msg":"获取成功","data":{"curPage":0,"pageCount":1,"size":10,"total":2,"collection":[{"id":1,"nickname":"asd","active":1,"email":"asd","groupId":1,"groupName":"张三","admin":1},{"id":2,"nickname":"ww","active":1,"email":"ww","groupId":2,"groupName":"aa","admin":1}]}}
     */
    @AdminRequired
    @GetMapping("/users")
    @RouteMeta(auth = "查询所有用户", module = "管理员", mount = false)
    public BaseResponse getAdminUsers(AdminUserArgs args) {
        // 校验页码
        checkPaginate(args);

        PageCard<AdminUserCard> pageCard = adminResource.getAdminUsers(args);

        return Response.success(pageCard);
    }

    @AdminRequired
    @PutMapping("/password/{id}")
    @RouteMeta(auth = "修改用户密码", module = "管理员", mount = false)
    public BaseResponse ChangePassword(@PathVariable Integer id,
                                       @Valid ResetPasswordArgs args) {

        adminResource.changePassword(id, args);

        return Response.success("密码修改成功");
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @RouteMeta(auth = "删除用户", module = "管理员", mount = false)
    @Logger(template = "管理员删除了一个用户")
    public BaseResponse deleteUser(@PathVariable Integer id) {
        adminResource.deleteUser(id);
        return Response.success("操作成功");
    }


    @AdminRequired
    @PutMapping("/{id}")
    @RouteMeta(auth = "管理员更新用户信息", module = "管理员", mount = false)
    public BaseResponse updateUser(@PathVariable Integer id, @Valid UpdateUserArgs args) {

        adminResource.updateUser(id, args);

        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/disable/{id}")
    @RouteMeta(auth = "禁用用户", module = "管理员", mount = false)
    public BaseResponse transDisable(@PathVariable Integer id) {

        adminResource.transDisable(id);
        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/active/{id}")
    @RouteMeta(auth = "激活用户", module = "管理员", mount = false)
    public BaseResponse transActive(@PathVariable Integer id) {

        adminResource.transActive(id);
        return Response.success("操作成功");
    }


    @AdminRequired
    @GetMapping("/groups")
    @RouteMeta(auth = "查询所有权限组及其权限", module = "管理员", mount = false)
    public BaseResponse getAdminGroups(PageArgs args) {

        checkPaginate(args);

        PageCard<GroupInfoCard> groupInfoCards = adminResource.getAdminGroups(args);

        return Response.success(groupInfoCards);
    }

    @AdminRequired
    @GetMapping("/group/all")
    @RouteMeta(auth = "查询所有权限组", module = "管理员", mount = false)
    public BaseResponse getAllGroups(PageArgs args) {

        checkPaginate(args);

        PageCard<GroupCard> groupCards = adminResource.getAllGroups(args);

        return Response.success(groupCards);
    }

    @AdminRequired
    @GetMapping("/group/{id}")
    @RouteMeta(auth = "查询一个权限组及其权限", module = "管理员", mount = false)
    public BaseResponse getGroup(@PathVariable Integer id) {

        GroupInfoCard groupInfoCard = adminResource.getGroup(id);

        return Response.success(groupInfoCard);
    }


    @AdminRequired
    @PostMapping("/group")
    @Logger(template = "管理员新建了一个权限组")
    @RouteMeta(auth = "新建权限组", module = "管理员", mount = false)
    public BaseResponse createGroup(@Valid NewGroupArgs args) {

        adminResource.createGroup(args);

        return Response.success("新建分组成功");
    }

    @AdminRequired
    @PutMapping("/group/{id}")
    @RouteMeta(auth = "更新一个权限组", module = "管理员", mount = false)
    public BaseResponse updateGroup(@PathVariable Integer id, @Valid UpdateGroupArgs args) {

        adminResource.updateGroup(id, args);

        return Response.success("更新分组成功");
    }

    @AdminRequired
    @DeleteMapping("/group/{id}")
    @Logger(template = "管理员删除一个权限组")
    @RouteMeta(auth = "删除一个权限组", module = "管理员", mount = false)
    public BaseResponse deleteGroup(@PathVariable Integer id) {

        adminResource.deleteGroup(id);

        return Response.success("删除分组成功");
    }


    @AdminRequired
    @PostMapping("/dispatch")
    @RouteMeta(auth = "分配单个权限", module = "管理员", mount = false)
    public BaseResponse dispatchAuth(@Valid DispatchAuthArgs args) {

        adminResource.dispatchAuth(args);

        return Response.success("添加权限成功");
    }

    @AdminRequired
    @PostMapping("/dispatch/patch")
    @RouteMeta(auth = "分配多个权限", module = "管理员", mount = false)
    public BaseResponse dispatchAuths(@Valid DispatchAuthsArgs args) {

        adminResource.dispatchAuths(args);

        return Response.success("添加权限成功");
    }

    @AdminRequired
    @PostMapping("/remove")
    @RouteMeta(auth = "删除多个权限", module = "管理员", mount = false)
    public BaseResponse removeAuths(@Valid RemoveAuthsArgs args) {

        adminResource.removeAuths(args);

        return Response.success("删除权限成功");
    }
}
