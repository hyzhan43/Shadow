package com.example.core.service.user;

import com.example.core.bean.args.LoginArgs;
import com.example.core.bean.args.RegisterArgs;
import com.example.core.bean.card.TokenCard;
import com.example.core.bean.db.User;
import com.example.core.config.Setting;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.UserRepository;
import com.example.core.service.BaseService;
import com.example.core.service.log.LogService;
import com.example.core.utils.JWTUtils;
import com.example.core.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Service
public class UserService extends BaseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogService logService;

    // 获取当前用户
    public User getCurrentUser() {
        return getUser(Integer.parseInt(getCurrentUid()));
    }

    public User getUser(Integer uid) {
        Optional<User> userOptional = userRepository.findById(uid);

        // 如果不存在
        if (!userOptional.isPresent()) {
            throw new BaseException(ErrorCode.USER_NOT_EXIST);
        }

        return userOptional.get();
    }

    @Transactional
    public TokenCard login(LoginArgs args) {
        Optional<User> userOptional = userRepository.findByNickname(args.getNickname());

        if (!userOptional.isPresent()) {
            throw new BaseException(ErrorCode.USER_NOT_EXIST);
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(Utils.encode(args.getPassword()))) {
            // 密码不相等，抛出异常
            throw new BaseException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }

        // 写入日志
        logService.saveLoginLog(user);

        return getToken(user.getId().toString());
    }

    private TokenCard getToken(String uid) {
        String accessToken = JWTUtils.createToken(uid, Setting.TOKEN_TIME);
        String refreshToken = JWTUtils.createToken(uid, Setting.REFRESH_TOKEN_TIME);

        return new TokenCard(accessToken, refreshToken);
    }

    public void register(RegisterArgs args) {

        // 判断两次密码是否输入正确
        if (!args.getConfirmPassword().equals(args.getPassword())) {
            throw new BaseException(ErrorCode.CONFIRM_PASSWORD_ERROR);
        }

        Optional<User> userOptional;

        userOptional = userRepository.findByNickname(args.getNickname());
        // 判断用户名是否重复
        if (userOptional.isPresent()) {
            throw new BaseException(ErrorCode.NICKNAME_EXIST);
        }

        if (!args.getEmail().isEmpty()) {
            userOptional = userRepository.findByEmail(args.getEmail());
            if (userOptional.isPresent()) {
                throw new BaseException(ErrorCode.EMAIL_EXIST);
            }
        }

        registerUser(args);
    }

    private void registerUser(RegisterArgs args) {
        User user = new User();

        // md5 加密后存储
        String mdPassword = Utils.encode(args.getPassword());
        args.setPassword(mdPassword);

        BeanUtils.copyProperties(args, user);

        userRepository.save(user);
    }

    public void emailIsPresent(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new BaseException(ErrorCode.EMAIL_EXIST);
        }
    }
}
