package com.zhan.core.service;

import com.zhan.core.bean.card.RouteMetaCard;
import com.zhan.core.bean.db.Log;
import com.zhan.core.bean.db.User;
import com.zhan.core.bean.model.LogModel;
import com.zhan.core.repository.LogRepository;
import com.zhan.core.utils.RouteMetaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/19
 * desc：    TODO
 */
@Service
public class LogService {

    private LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void saveLog(LogModel model) {

        Log log = new Log();
        BeanUtils.copyProperties(model, log);

        RouteMetaCard routeMetaCard = RouteMetaUtil.getRouteMetaCard(model.getMethodName());

        if (routeMetaCard != null) {
            log.setAuthority(routeMetaCard.getAuth());
        }

        String message = parseTemplate(model.getTemplate());
        log.setMessage(message);

        logRepository.save(log);
    }

    private String parseTemplate(String template) {
        return template;
    }

    public void saveLoginLog(User user) {
        Log log = new Log();
        log.setUserId(user.getId());
        log.setUsername(user.getNickname());
        log.setStatusCode(200);
        log.setMethod("POST");
        log.setPath("/user/login");
        log.setMessage(user.getNickname() + "登陆成功获取了令牌");

        logRepository.save(log);
    }

    public Page<Log> getAllLogs(Specification<Log> specification, Pageable pageable) {
        return logRepository.findAll(specification, pageable);
    }

    public Page<Log> getLogs(String name, Date start, Date end, Pageable pageable) {
        return logRepository.getLogByAndNameAndTime(name, start, end, pageable);
    }

    public Page<Log> getUserLogs(String keyword, String name, Date start, Date end, Pageable pageable) {
        return logRepository.getLogByKeywordAndNameAndTime(keyword, name, start, end, pageable);
    }

}
