package com.example.core.resource;

import com.example.core.bean.args.LogArgs;
import com.example.core.bean.args.PageArgs;
import com.example.core.bean.args.UserLogArgs;
import com.example.core.bean.card.LogCard;
import com.example.core.bean.card.PageCard;
import com.example.core.bean.db.Log;
import com.example.core.bean.db.User;
import com.example.core.bean.args.LogInfoArgs;
import com.example.core.bean.model.LogModel;
import com.example.core.service.LogService;
import com.example.core.service.UserService;
import com.example.core.utils.DateUtils;
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
import java.util.List;

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

        Date start = DateUtils.stringFormat(args.getStart());
        Date end = DateUtils.stringFormat(args.getEnd());

        Page<LogCard> logCardPage = logService.getLogs(args.getName(), start, end, pageable)
                .map(LogCard::new);

        return convertPageCard(logCardPage);
    }

    public PageCard<LogCard> getUserLogs(String keyword, LogArgs args) {

        Pageable pageable = prepareCondition(args);

        Date start = DateUtils.stringFormat(args.getStart());
        Date end = DateUtils.stringFormat(args.getEnd());

        Page<LogCard> logList = logService.getUserLogs(keyword,
                args.getName(), start, end, pageable)
                .map(LogCard::new);

        return convertPageCard(logList);
    }

    private Pageable prepareCondition(LogArgs args) {
        return PageRequest.of(args.getPage(),
                args.getCount(),
                Sort.Direction.DESC,
                "create_time");
    }

    public List<String> getUsers(PageArgs args) {

        Pageable pageable = PageRequest.of(args.getPage(), args.getCount());

        Specification<Log> specification = (Specification<Log>) (root, criteriaQuery, criteriaBuilder) -> {
            Path path = root.get("username");
            // 设置查询条件 按照 username 分组
            criteriaQuery.groupBy(path);
            //这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件Predicate给Spring Data Jpa，故最后return null
            return null;
        };

        Page<String> logPage = logService.getAllLogs(specification, pageable)
                .map(Log::getUsername);

        return logPage.getContent();
    }

    public void saveLog(LogInfoArgs args) {

        User user = userService.getUserById(args.getUserId());

        LogModel logModel = new LogModel();
        BeanUtils.copyProperties(args, logModel);
        logModel.setUsername(user.getNickname());

        logService.saveLog(logModel);
    }
}
