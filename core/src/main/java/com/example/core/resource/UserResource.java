package com.example.core.resource;

import com.example.core.bean.args.LoginArgs;
import com.example.core.bean.args.RegisterArgs;
import com.example.core.bean.card.TokenCard;
import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import com.example.core.config.Setting;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.service.GroupService;
import com.example.core.service.LogService;
import com.example.core.service.UserService;
import com.example.core.utils.TokenUtils;
import com.example.core.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author：  HyZhan
 * create：  2019/4/23
 * desc：    TODO
 */
@Component
public class UserResource {

    private UserService userService;

    private LogService logService;

    private GroupService groupService;

    @Autowired
    public UserResource(UserService userService, LogService logService, GroupService groupService) {
        this.userService = userService;
        this.logService = logService;
        this.groupService = groupService;
    }

    public TokenCard login(LoginArgs args) {

        User user = userService.getUserByNickname(args.getNickname());

        if (!user.getPassword().equals(Utils.encode(args.getPassword()))) {
            // 密码不相等，抛出异常
            throw new BaseException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }

        // 写入日志
        logService.saveLoginLog(user);

        String uid = user.getId().toString();

        String accessToken = TokenUtils.createToken(uid, Setting.TOKEN_TIME);
        String refreshToken = TokenUtils.createToken(uid, Setting.REFRESH_TOKEN_TIME);

        return new TokenCard(accessToken, refreshToken);
    }

    public void register(RegisterArgs args) {
        // 判断两次密码是否输入正确
        if (!args.getConfirmPassword().equals(args.getPassword())) {
            throw new BaseException(ErrorCode.CONFIRM_PASSWORD_ERROR);
        }

        userService.findUserByNickname(args.getNickname());

        if (!args.getEmail().isEmpty()) {
            userService.findUserByEmail(args.getEmail());
        }

        Group group = groupService.getGroupById(args.getGroupId());

        userService.registerUser(args, group);
    }
}
