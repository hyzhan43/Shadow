package com.example.app.controller.cms;

import com.example.core.annotation.AdminRequired;
import com.example.core.annotation.Logger;
import com.example.core.annotation.RouteMeta;
import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.bean.args.AdminUserArgs;
import com.example.core.bean.args.ChangePasswordArgs;
import com.example.core.bean.args.UpdateUserArgs;
import com.example.core.bean.card.RouteMetaCard;
import com.example.core.controller.BaseController;
import com.example.core.service.admin.AdminUsersService;
import com.example.core.utils.RouteMetaUtil;
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
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    AdminUsersService adminUsersService;

    /**
     * @api {get} /admin/authority 获取所有可分配权限
     * @apiGroup admin
     * @apiVersion 1.0.0
     * @apiSuccessExample {json} 返回样例：
     * {"code":0,"msg":"获取成功","data":[{"auth":"查询所有用户","module":"管理员","mount":false},{"auth":"删除用户","module":"管理员","mount":false},{"auth":"修改用户密码","module":"管理员","mount":false}]}
     */
    @AdminRequired
    @GetMapping("/authority")
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
    @GetMapping("/users")
    @RouteMeta(auth = "查询所有用户", module = "管理员", mount = false)
    public BaseResponse getAdminUsers(@Valid AdminUserArgs args) {
        // 校验页码
        checkPaginate(args);

        return Response.success(adminUsersService.getAdminUsers(args));
    }

    @PutMapping("/password/{id}")
    @RouteMeta(auth = "修改用户密码", module = "管理员", mount = false)
    public BaseResponse ChangePassword(@PathVariable Integer id, @Valid @RequestBody ChangePasswordArgs args) {

        adminUsersService.changePassword(id, args);

        return Response.success("密码修改成功");
    }

    @DeleteMapping("/{id}")
    @RouteMeta(auth = "删除用户", module = "管理员", mount = false)
    public BaseResponse deleteUser(@PathVariable Integer id) {
        adminUsersService.deleteUser(id);
        return Response.success("操作成功");
    }


    @PutMapping("/{id}")
    @RouteMeta(auth = "管理员更新用户信息", module = "管理员", mount = false)
    @AdminRequired
    public BaseResponse updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserArgs args) {

        adminUsersService.updateUser(id, args);

        return Response.success("操作成功");
    }

    @AdminRequired
    @PutMapping("/disable/{id}")
    @RouteMeta(auth = "禁用用户", module = "管理员", mount = false)
    public BaseResponse transDisable(@PathVariable Integer id) {

        adminUsersService.transDisable(id);
        return Response.success("操作成功");
    }

}
