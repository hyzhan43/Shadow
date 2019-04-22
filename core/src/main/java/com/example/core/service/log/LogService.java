package com.example.core.service.log;

import com.example.core.bean.card.RouteMetaCard;
import com.example.core.bean.db.Log;
import com.example.core.bean.db.User;
import com.example.core.repository.LogRepository;
import com.example.core.service.user.UserService;
import com.example.core.utils.L;
import com.example.core.utils.RouteMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/4/19
 * desc：    TODO
 */
@Service
public class LogService {

    @Autowired
    UserService userService;

    @Autowired
    LogRepository logRepository;

    public void saveLog(String uid, String template, Integer status, String method,
                        String url, String methodName) {

        User user = userService.getUser(Integer.parseInt(uid));

        Log log = new Log();
        log.setUserId(user.getId());
        log.setUsername(user.getNickname());
        log.setStatusCode(status);
        log.setMethod(method);
        log.setPath(url);

        RouteMetaCard routeMetaCard = RouteMetaUtil.findRouteMetaCard(methodName);

        if (routeMetaCard != null) {
            log.setAuthority(routeMetaCard.getAuth());
        }

        String message = parseTemplate(template);
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
}
