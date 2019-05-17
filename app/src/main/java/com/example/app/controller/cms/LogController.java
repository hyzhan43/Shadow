package com.example.app.controller.cms;

import com.example.core.annotation.GroupRequired;
import com.example.core.annotation.RouteMeta;
import com.example.core.bean.BaseResponse;
import com.example.core.bean.Response;
import com.example.core.bean.args.LogArgs;
import com.example.core.bean.args.PageArgs;
import com.example.core.bean.args.UserLogArgs;
import com.example.core.bean.card.LogCard;
import com.example.core.bean.card.PageCard;
import com.example.core.bean.card.ResponseCard;
import com.example.core.bean.db.Log;
import com.example.core.controller.BaseController;
import com.example.core.resource.LogResource;
import com.example.core.utils.L;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Api(tags = "日志模块")
@RestController
@RequestMapping("/cms/log")
public class LogController extends BaseController {

    private LogResource logResource;

    @Autowired
    public LogController(LogResource logResource) {
        this.logResource = logResource;
    }

    @GroupRequired
    @GetMapping("/")
    @ApiOperation("查询所有日志")
    @RouteMeta(auth = "查询所有日志", module = "日志")
    public PageCard getLogs(@RequestBody LogArgs args) {

        checkPaginate(args);

        return logResource.getLogs(args);
    }

    @GroupRequired
    @GetMapping("/search")
    @ApiOperation("搜索日志")
    @RouteMeta(auth = "搜索日志", module = "日志")
    public PageCard getUserLogs(@ApiParam(value = "关键词", required = true)
                                @RequestParam(name = "keyword") String keyword,
                                @RequestBody LogArgs args) {

        checkPaginate(args);

        return logResource.getUserLogs(keyword, args);
    }

    @GroupRequired
    @GetMapping("/users")
    @ApiOperation("查询日志记录的用户")
    @RouteMeta(auth = "查询日志记录的用户", module = "日志")
    public List<String> getUsers(PageArgs args) {

        checkPaginate(args);

        return logResource.getUsers(args);
    }
}
