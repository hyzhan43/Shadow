package com.example.core.service;

import com.example.core.bean.args.RegisterArgs;
import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.repository.UserRepository;
import com.example.core.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Service
public class UserService extends BaseService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 获取当前用户
    public User getCurrentUser() {
        return getUserById(Integer.parseInt(getCurrentUid()));
    }

    public User getUserById(Integer uid) {
        Optional<User> userOptional = userRepository.findById(uid);

        // 如果不存在
        if (!userOptional.isPresent()) {
            throw new BaseException(ErrorCode.USER_NOT_EXIST);
        }

        return userOptional.get();
    }


    public void registerUser(RegisterArgs args, Group group) {
        User user = new User();

        // md5 加密后存储
        String mdPassword = Utils.encode(args.getPassword());
        args.setPassword(mdPassword);

        user.setNickname(args.getNickname());
        user.setPassword(mdPassword);
        user.setEmail(args.getEmail());
        user.setGroup(group);

        userRepository.save(user);
    }

    public void emailIsPresent(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new BaseException(ErrorCode.EMAIL_EXIST);
        }
    }

    public User getUserByNickname(String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);

        if (!userOptional.isPresent()) {
            throw new BaseException(ErrorCode.USER_NOT_EXIST);
        }

        return userOptional.get();
    }

    public void findUserByNickname(String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);

        if (userOptional.isPresent()) {
            throw new BaseException(ErrorCode.NICKNAME_EXIST);
        }
    }

    public void findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new BaseException(ErrorCode.EMAIL_EXIST);
        }
    }

    public Page<User> getUserByAdmin(Integer admin, Pageable pageable) {
        return userRepository.findByAdmin(admin, pageable);
    }

    public Page<User> getUserByAdminAndGroupId(Integer admin, Integer groupId, Pageable pageable) {
        return userRepository.findByAdminAndGroupId(admin, groupId, pageable);
    }

    public void changePassword(Integer id, String newPassword) {
        User user = getUserById(id);
        user.setPassword(Utils.encode(newPassword));

        userRepository.save(user);
    }

    public void deleteUserById(Integer id) {
        User user = getUserById(id);
        // 硬删除
        userRepository.delete(user);
    }

    public void updateUserEmailAndGroup(User user, String email, Group group) {
        user.setGroup(group);
        user.setEmail(email);
        userRepository.save(user);
    }

    public void transUserStatus(User user, int status) {
        user.setActive(status);
        userRepository.save(user);
    }

    public void findUserByGroupId(Integer gid) {
        Optional<User> userOptional = userRepository.findByGroupId(gid);

        if (userOptional.isPresent()) {
            throw new BaseException(ErrorCode.GROUP_EXIST_USER);
        }
    }

    public void updateUserEmail(User user, String email) {
        user.setEmail(email);
        userRepository.save(user);
    }

    public void updateUserPassword(User user, String oldPassword) {
        user.setPassword(Utils.encode(oldPassword));
        userRepository.save(user);
    }
}
