package com.example.hyzhan.controller.cms;

import com.example.hyzhan.annotation.AdminRequired;
import com.example.hyzhan.annotation.LoginRequired;
import com.example.hyzhan.annotation.RouteMeta;
import com.example.hyzhan.bean.BaseResponse;
import com.example.hyzhan.bean.Response;
import com.example.hyzhan.bean.args.AdminUserArgs;
import com.example.hyzhan.bean.card.RouteMetaCard;
import com.example.hyzhan.controller.BaseController;
import com.example.hyzhan.service.admin.AdminUsersService;
import com.example.hyzhan.utils.RouteMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

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
    public void ChangePassword(@PathVariable Integer id) {

    }

    @DeleteMapping("/{id}")
    @RouteMeta(auth = "删除用户", module = "管理员", mount = false)
    public void deleteUser(@PathVariable Integer id) {

    }
}
