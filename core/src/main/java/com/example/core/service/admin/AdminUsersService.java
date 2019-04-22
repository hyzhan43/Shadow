package com.example.core.service.admin;

import com.example.core.bean.args.AdminUserArgs;
import com.example.core.bean.args.ChangePasswordArgs;
import com.example.core.bean.args.UpdateUserArgs;
import com.example.core.bean.card.AdminUserCard;
import com.example.core.bean.card.PageCard;
import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.UserRepository;
import com.example.core.service.PageService;
import com.example.core.service.group.GroupService;
import com.example.core.service.user.UserService;
import com.example.core.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Service
public class AdminUsersService extends PageService {

    @Autowired
    UserRepository usersRepository;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    public PageCard getAdminUsers(AdminUserArgs args) {

        Integer groupId = args.getGroupId();

        // 分页查询条件
        Pageable pageable = PageRequest.of(args.getPage(), args.getPageSize());

        Page<User> userPage;
        if (groupId == null) {
            userPage = usersRepository.findByAdmin(User.COMMON, pageable);
        } else {
            userPage = usersRepository.findByAdminAndGroupId(User.COMMON, groupId, pageable);
        }

        Page<AdminUserCard> page = userPage.map(AdminUserCard::new);

        return convertPageCard(page);
    }

    public void changePassword(Integer uid, ChangePasswordArgs args) {

        String newPassword = args.getNewPassword();
        if (!newPassword.equals(args.getConfirmPassword())) {
            throw new BaseException(ErrorCode.CONFIRM_PASSWORD_ERROR);
        }

        User user = userService.getUser(uid);

        user.setPassword(Utils.encode(newPassword));
        usersRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userService.getUser(id);
        // 硬删除
        usersRepository.delete(user);
    }

    public void updateUser(Integer id, UpdateUserArgs args) {
        User user = userService.getUser(id);

        String email = args.getEmail();
        if (!user.getEmail().equals(email)){
            userService.emailIsPresent(email);
        }

        Integer groupId = args.getGroupId();
        Group group = groupService.findById(groupId);

        user.setGroup(group);
        user.setEmail(email);
        usersRepository.save(user);
    }

    public void transDisable(Integer id) {

        User user = userService.getUser(id);
        if (user.getActive() != User.ACTIVE){
            throw new BaseException(ErrorCode.FORBIDDEN);
        }

        user.setActive(User.FORBID);
        usersRepository.save(user);
    }
}
