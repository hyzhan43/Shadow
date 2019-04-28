package com.example.core.resource;

import com.example.core.bean.args.*;
import com.example.core.bean.card.*;
import com.example.core.bean.db.Auth;
import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import com.example.core.config.Setting;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.service.AuthService;
import com.example.core.service.GroupService;
import com.example.core.service.LogService;
import com.example.core.service.UserService;
import com.example.core.utils.TokenUtils;
import com.example.core.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    private AuthService authService;

    @Autowired
    public UserResource(UserService userService, LogService logService,
                        GroupService groupService, AuthService authService) {
        this.userService = userService;
        this.logService = logService;
        this.groupService = groupService;
        this.authService = authService;
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

    public void updateInfo(UpdateInfoArgs args) {
        User user = userService.getCurrentUser();

        String email = args.getEmail();
        if (!user.getEmail().equals(email)) {
            userService.findUserByEmail(email);

            userService.updateUserEmail(user, email);
        }
    }

    public void changePassword(ChangePasswordArgs args) {

        if (!args.getNewPassword().equals(args.getConfirmPassword())) {
            throw new BaseException(ErrorCode.CONFIRM_PASSWORD_ERROR);
        }

        User user = userService.getCurrentUser();

        userService.updateUserPassword(user, args.getOldPassword());
    }

    public UserCard getUserInfo() {
        User user = userService.getCurrentUser();
        return new UserCard(user);
    }

    public TokenCard refreshToken(RefreshTokenArgs args) {

        // 验证 token
        String uid = TokenUtils.parseToken(args.getRefreshToken());

        if (uid == null || uid.isEmpty()) {
            throw new BaseException(ErrorCode.REFRESH_ERROR);
        }

        String token = TokenUtils.createToken(uid, Setting.TOKEN_DELAYED_TIME);
        return new TokenCard(token, args.getRefreshToken());
    }

    public UserApisCard getAllowedApis() {

        User user = userService.getCurrentUser();
        List<Auth> authList = authService.getAuthByGroupId(user.getGroupId());

        List<AuthCard> authCards = authList.stream()
                .map(AuthCard::new)
                .collect(Collectors.toList());

        return new UserApisCard(user, authCards);
    }
}
