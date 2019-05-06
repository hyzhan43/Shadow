package com.example.app.controller.cms;

import com.example.core.annotation.AdminRequired;
import com.example.core.annotation.Logger;
import com.example.core.annotation.RouteMeta;
import com.example.core.bean.Response;
import com.example.core.bean.args.*;
import com.example.core.bean.card.*;
import com.example.core.controller.BaseController;
import com.example.core.resource.AdminResource;
import com.example.core.utils.L;
import com.example.core.utils.RouteMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * author：  HyZhan
 * create：  2019/4/6
 * desc：    TODO
 */

@RestController
@RequestMapping("cms/admin")
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
     * {"图书":{"删除图书":["[/v1/book]+deleteBook"]},"日志":{"查询所有日志":["[/cms/log]+getLogs"],"查询日志记录的用户":["[/cms/log]+getUsers"],"搜索日志":["[/cms/log]+getUserLogs"]}}
     */
    @AdminRequired
    @GetMapping("/authority")
    @RouteMeta(auth = "查询所有可分配的权限", module = "管理员", mount = false)
    public Map<String, Map<String, List<String>>> authority() {
        return RouteMetaUtil.getAuthorityInfo();
    }

    @AdminRequired
    @GetMapping("/users")
    @RouteMeta(auth = "查询所有用户", module = "管理员", mount = false)
    public PageCard getAdminUsers(@RequestBody AdminUserArgs args) {
        // 校验页码
        checkPaginate(args);

        return adminResource.getAdminUsers(args);
    }

    @AdminRequired
    @PutMapping("/password/{id}")
    @RouteMeta(auth = "修改用户密码", module = "管理员", mount = false)
    public ResponseCard ChangePassword(@PathVariable Integer id,
                                       @Valid @RequestBody ResetPasswordArgs args) {

        adminResource.changePassword(id, args);

        return Response.success("密码修改成功");
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @RouteMeta(auth = "删除用户", module = "管理员", mount = false)
    @Logger(template = "管理员删除了一个用户")
    public ResponseCard deleteUser(@PathVariable Integer id) {
        adminResource.deleteUser(id);
        return Response.success("操作成功");
    }


    @AdminRequired
    @PutMapping("/{id}")
    @RouteMeta(auth = "管理员更新用户信息", module = "管理员", mount = false)
    public ResponseCard updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserArgs args) {

        adminResource.updateUser(id, args);

        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/disable/{id}")
    @RouteMeta(auth = "禁用用户", module = "管理员", mount = false)
    public ResponseCard transDisable(@PathVariable Integer id) {

        adminResource.transDisable(id);
        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/active/{id}")
    @RouteMeta(auth = "激活用户", module = "管理员", mount = false)
    public ResponseCard transActive(@PathVariable Integer id) {

        adminResource.transActive(id);
        return Response.success("操作成功");
    }


    @AdminRequired
    @GetMapping("/groups")
    @RouteMeta(auth = "查询所有权限组及其权限", module = "管理员", mount = false)
    public PageCard getAdminGroups(PageArgs args) {

        checkPaginate(args);

        return adminResource.getAdminGroups(args);
    }

    @AdminRequired
    @GetMapping("/group/all")
    @RouteMeta(auth = "查询所有权限组", module = "管理员", mount = false)
    public List<GroupCard> getAllGroups(PageArgs args) {

        checkPaginate(args);

        return adminResource.getAllGroups(args);
    }

    @AdminRequired
    @GetMapping("/group/{id}")
    @RouteMeta(auth = "查询一个权限组及其权限", module = "管理员", mount = false)
    public GroupInfoCard getGroup(@PathVariable Integer id) {
        return adminResource.getGroup(id);
    }


    @AdminRequired
    @PostMapping("/group")
    @Logger(template = "管理员新建了一个权限组")
    @RouteMeta(auth = "新建权限组", module = "管理员", mount = false)
    public ResponseCard createGroup(@Valid @RequestBody NewGroupArgs args) {

        adminResource.createGroup(args);

        return Response.success("新建分组成功");
    }

    @AdminRequired
    @PutMapping("/group/{id}")
    @RouteMeta(auth = "更新一个权限组", module = "管理员", mount = false)
    public ResponseCard updateGroup(@PathVariable Integer id,
                                    @Valid @RequestBody UpdateGroupArgs args) {

        adminResource.updateGroup(id, args);

        return Response.success("更新分组成功");
    }

    @AdminRequired
    @DeleteMapping("/group/{id}")
    @Logger(template = "管理员删除一个权限组")
    @RouteMeta(auth = "删除一个权限组", module = "管理员", mount = false)
    public ResponseCard deleteGroup(@PathVariable Integer id) {

        adminResource.deleteGroup(id);

        return Response.success("删除分组成功");
    }


    @AdminRequired
    @PostMapping("/dispatch")
    @RouteMeta(auth = "分配单个权限", module = "管理员", mount = false)
    public ResponseCard dispatchAuth(@Valid @RequestBody DispatchAuthArgs args) {

        adminResource.dispatchAuth(args);

        return Response.success("添加权限成功");
    }

    @AdminRequired
    @PostMapping("/dispatch/patch")
    @RouteMeta(auth = "分配多个权限", module = "管理员", mount = false)
    public ResponseCard dispatchAuths(@Valid @RequestBody DispatchAuthsArgs args) {

        adminResource.dispatchAuths(args);

        return Response.success("添加权限成功");
    }

    @AdminRequired
    @PostMapping("/remove")
    @RouteMeta(auth = "删除多个权限", module = "管理员", mount = false)
    public ResponseCard removeAuths(@Valid @RequestBody RemoveAuthsArgs args) {

        adminResource.removeAuths(args);

        return Response.success("删除权限成功");
    }
}
