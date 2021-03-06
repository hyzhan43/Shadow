package com.zhan.core.resource;

import com.zhan.core.bean.args.LogArgs;
import com.zhan.core.bean.args.PageArgs;
import com.zhan.core.bean.args.UserLogArgs;
import com.zhan.core.bean.card.LogCard;
import com.zhan.core.bean.card.PageCard;
import com.zhan.core.bean.db.Log;
import com.zhan.core.bean.db.User;
import com.zhan.core.bean.args.LogInfoArgs;
import com.zhan.core.bean.model.LogModel;
import com.zhan.core.service.LogService;
import com.zhan.core.service.UserService;
import com.zhan.core.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Component
public class LogResource extends PageResource {

    private LogService logService;

    private UserService userService;

    @Autowired
    public LogResource(LogService logService, UserService userService) {
        this.logService = logService;
        this.userService = userService;
    }

    public PageCard<LogCard> getLogs(LogArgs args) {

        Pageable pageable = prepareCondition(args);

        Date start = DateUtils.stringFormat(args.getStartTime());
        Date end = DateUtils.stringFormat(args.getEndTime());

        Page<LogCard> logCardPage = logService.getLogs(args.getName(), start, end, pageable)
                .map(LogCard::new);

        return convertPageCard(logCardPage);
    }

    public PageCard<LogCard> getUserLogs(UserLogArgs args) {

        Pageable pageable = prepareCondition(args);

        Date start = DateUtils.stringFormat(args.getStartTime());
        Date end = DateUtils.stringFormat(args.getEndTime());

        Page<LogCard> logList = logService.getUserLogs(args.getKeyword(),
                args.getName(), start, end, pageable)
                .map(LogCard::new);

        return convertPageCard(logList);
    }

    private Pageable prepareCondition(LogArgs args) {
        return PageRequest.of(args.getPage(),
                args.getPageSize(),
                Sort.Direction.DESC,
                "create_time");
    }

    public PageCard<String> getUsers(PageArgs args) {

        Pageable pageable = PageRequest.of(args.getPage(), args.getPageSize());

        Specification<Log> specification = (Specification<Log>) (root, criteriaQuery, criteriaBuilder) -> {
            Path path = root.get("username");
            // 设置查询条件 按照 username 分组
            criteriaQuery.groupBy(path);
            //这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件Predicate给Spring Data Jpa，故最后return null
            return null;
        };

        Page<String> logPage = logService.getAllLogs(specification, pageable)
                .map(Log::getUsername);

        return convertPageCard(logPage);
    }

    public void saveLog(LogInfoArgs args) {

        User user = userService.getUserById(args.getUserId());

        LogModel logModel = new LogModel();
        BeanUtils.copyProperties(args, logModel);
        logModel.setUsername(user.getNickname());

        logService.saveLog(logModel);
    }
}
