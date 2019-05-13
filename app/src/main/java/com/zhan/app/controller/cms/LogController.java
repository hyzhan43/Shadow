package com.zhan.app.controller.cms;

import com.zhan.core.annotation.GroupRequired;
import com.zhan.core.annotation.RouteMeta;
import com.zhan.core.bean.BaseResponse;
import com.zhan.core.bean.Response;
import com.zhan.core.bean.args.LogArgs;
import com.zhan.core.bean.args.PageArgs;
import com.zhan.core.bean.args.UserLogArgs;
import com.zhan.core.bean.card.LogCard;
import com.zhan.core.bean.card.PageCard;
import com.zhan.core.controller.BaseController;
import com.zhan.core.resource.LogResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@RestController
@RequestMapping("/cms/log")
public class LogController extends BaseController {

    private LogResource logResource;

    @Autowired
    public LogController(LogResource logResource) {
        this.logResource = logResource;
    }

    @GroupRequired
    @GetMapping("")
    @RouteMeta(auth = "查询所有日志", module = "日志", mount = false)
    public BaseResponse getLogs(LogArgs args) {

        checkPaginate(args);

        PageCard<LogCard> logPageCard = logResource.getLogs(args);

        return Response.success(logPageCard);
    }

    @GroupRequired
    @GetMapping("/search")
    @RouteMeta(auth = "搜索日志", module = "日志", mount = false)
    public BaseResponse getUserLogs(@Valid UserLogArgs args) {

        checkPaginate(args);

        PageCard<LogCard> logPageCard = logResource.getUserLogs(args);

        return Response.success(logPageCard);
    }

    @GroupRequired
    @GetMapping("/users")
    @RouteMeta(auth = "查询日志记录的用户", module = "日志")
    public BaseResponse getUsers(PageArgs args) {

        checkPaginate(args);

        PageCard<String> logPageCar = logResource.getUsers(args);

        return Response.success(logPageCar);
    }
}
