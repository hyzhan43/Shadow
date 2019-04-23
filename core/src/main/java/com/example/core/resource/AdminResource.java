package com.example.core.resource;

import com.example.core.bean.args.AdminUserArgs;
import com.example.core.bean.args.BaseArgs;
import com.example.core.bean.args.ChangePasswordArgs;
import com.example.core.bean.args.UpdateUserArgs;
import com.example.core.bean.card.*;
import com.example.core.bean.db.Auth;
import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.service.AuthService;
import com.example.core.service.PageResource;
import com.example.core.service.GroupService;
import com.example.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author：  HyZhan
 * create：  2019/4/23
 * desc：    TODO
 */
@Component
public class AdminResource extends PageResource {

    private UserService userService;

    private GroupService groupService;

    private AuthService authService;

    @Autowired
    public AdminResource(UserService userService, GroupService groupService,
                         AuthService authService) {
        this.userService = userService;
        this.groupService = groupService;
        this.authService = authService;
    }

    public PageCard<AdminUserCard> getAdminUsers(AdminUserArgs args) {

        Integer groupId = args.getGroupId();

        // 分页查询条件
        Pageable pageable = PageRequest.of(args.getPage(), args.getPageSize());

        Page<User> userPage;
        if (groupId == null) {
            userPage = userService.getUserByAdmin(User.COMMON, pageable);
        } else {
            userPage = userService.getUserByAdminAndGroupId(User.COMMON, groupId, pageable);
        }

        Page<AdminUserCard> page = userPage.map(AdminUserCard::new);

        return convertPageCard(page);
    }

    public void changePassword(Integer id, ChangePasswordArgs args) {
        String newPassword = args.getNewPassword();

        if (!newPassword.equals(args.getConfirmPassword())) {
            throw new BaseException(ErrorCode.CONFIRM_PASSWORD_ERROR);
        }

        userService.changePassword(id, newPassword);
    }

    public void deleteUser(Integer id) {
        userService.deleteUserById(id);
    }

    public void updateUser(Integer id, UpdateUserArgs args) {
        User user = userService.getUserById(id);

        String email = args.getEmail();
        if (!user.getEmail().equals(email)) {
            userService.emailIsPresent(email);
        }

        Integer groupId = args.getGroupId();
        Group group = groupService.getGroupById(groupId);

        userService.updateUserEmailAndGroup(user, email, group);
    }

    public void transDisable(Integer id) {
        User user = userService.getUserById(id);
        if (user.isForbid()) {
            throw new BaseException(ErrorCode.USER_IS_FORBID);
        }
        userService.transUserStatus(user, User.FORBID);
    }

    public void transActive(Integer id) {
        User user = userService.getUserById(id);
        if (!user.isForbid()){
            throw new BaseException(ErrorCode.USER_IS_ACTIVE);
        }

        userService.transUserStatus(user, User.ACTIVE);
    }

    public PageCard<GroupCard> getAdminGroups(BaseArgs args) {
        Pageable pageable = PageRequest.of(args.getPage(), args.getPageSize());

        Page<Group> groupPage = groupService.getAllGroup(pageable);

        Page<GroupCard> groupCardPage = groupPage.map(group -> {

            List<Auth> authList = authService.getAuthByGroupId(group.getId());

            // 按module 模块分组
            Map<String, List<AuthCard>> groupMap = authList.stream()
                    .map(AuthCard::new)
                    .collect(Collectors.groupingBy(AuthCard::getModule));

            List<ModuleCard> moduleCards = new ArrayList<>();
            groupMap.forEach((module, authCards) ->
                    moduleCards.add(new ModuleCard(module, authCards)));

            return new GroupCard(group, moduleCards);
        });

        return convertPageCard(groupCardPage);
    }
}
