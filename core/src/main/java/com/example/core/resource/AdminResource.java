package com.example.core.resource;

import com.example.core.bean.model.AuthModel;
import com.example.core.bean.args.*;
import com.example.core.bean.card.*;
import com.example.core.bean.db.Auth;
import com.example.core.bean.db.Group;
import com.example.core.bean.db.User;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.service.AuthService;
import com.example.core.service.GroupService;
import com.example.core.service.UserService;
import com.example.core.utils.RouteMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

        // 分页查询条件
        Pageable pageable = PageRequest.of(args.getPage(), args.getCount());

        Page<AdminUserCard> adminUserPage = userService.getUserByAdminAndGroupId(
                User.COMMON,
                args.getGroup_id(),
                pageable)
                .map(AdminUserCard::new);

        return convertPageCard(adminUserPage);
    }

    public void changePassword(Integer id, ResetPasswordArgs args) {
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
        if (!user.isForbid()) {
            throw new BaseException(ErrorCode.USER_IS_ACTIVE);
        }

        userService.transUserStatus(user, User.ACTIVE);
    }

    public PageCard<GroupInfoCard> getAdminGroups(PageArgs args) {
        Pageable pageable = PageRequest.of(args.getPage(), args.getCount());

        Page<Group> groupPage = groupService.getAllGroup(pageable);

        Page<GroupInfoCard> groupCardPage = groupPage.map(group -> {

            List<Auth> authList = authService.getAuthByGroupId(group.getId());

            // 按module 模块分组
            Map<String, List<AuthCard>> groupMap = authList.stream()
                    .map(AuthCard::new)
                    .collect(Collectors.groupingBy(AuthCard::getModule));

            List<ModuleCard> moduleCards = new ArrayList<>();
            groupMap.forEach((module, authCards) ->
                    moduleCards.add(new ModuleCard(module, authCards)));

            return new GroupInfoCard(group, moduleCards);
        });

        return convertPageCard(groupCardPage);
    }

    public PageCard<GroupCard> getAllGroups(PageArgs args) {

        Pageable pageable = PageRequest.of(args.getPage(), args.getCount());

        Page<GroupCard> groupCardPage = groupService.getAllGroup(pageable)
                .map(GroupCard::new);

        return convertPageCard(groupCardPage);
    }

    public GroupInfoCard getGroup(Integer id) {

        Group group = groupService.getGroupById(id);

        Map<String, List<AuthCard>> groupMap = authService.getAuthByGroupId(group.getId())
                .stream()
                .map(AuthCard::new)
                .collect(Collectors.groupingBy(AuthCard::getModule));

        List<ModuleCard> moduleCards = new ArrayList<>();

        groupMap.forEach((module, authCards) -> moduleCards.add(new ModuleCard(module, authCards)));

        return new GroupInfoCard(group, moduleCards);
    }

    @Transactional
    public void createGroup(NewGroupArgs args) {

        groupService.findGroupByName(args.getName());

        Integer groupId = groupService.createGroup(args);

        List<RouteMetaCard> routeMetaCards = args.getAuths().stream()
                .map(RouteMetaUtil::getRouteMetaCardByAuth)
                .peek(routeMetaCard -> {
                    AuthModel model = new AuthModel();
                    model.setAuth(routeMetaCard.getAuth());
                    model.setModule(routeMetaCard.getModule());
                    model.setGroupId(groupId);
                    authService.createAuth(model);
                })
                .collect(Collectors.toList());
    }

    public void updateGroup(Integer id, UpdateGroupArgs args) {
        Group group = groupService.getGroupById(id);

        groupService.updateGroup(group, args.getName(), args.getInfo());
    }

    @Transactional
    public void deleteGroup(Integer gid) {
        Group group = groupService.getGroupById(gid);

        userService.findUserByGroupId(gid);

        // 删除group拥有的权限
        authService.deleteAuthByGroupId(gid);

        groupService.deleteGroup(group);
    }

    public void dispatchAuth(DispatchAuthArgs args) {

        String auth = args.getAuth();
        Integer groupId = args.getGroupId();

        groupService.getGroupById(groupId);

        createAuth(auth, groupId);
    }

    private void createAuth(String auth, Integer groupId) {
        authService.findAuthByGroupIdAndAuth(groupId, auth);

        RouteMetaCard routeMetaCard = RouteMetaUtil.getRouteMetaCardByAuth(auth);

        AuthModel model = new AuthModel();
        model.setAuth(auth);
        model.setGroupId(groupId);
        model.setModule(routeMetaCard.getModule());
        authService.createAuth(model);
    }

    public void dispatchAuths(DispatchAuthsArgs args) {

        Integer gid = args.getGroupId();

        groupService.getGroupById(gid);

        List<String> authList = args.getAuths().stream()
                .peek(auth -> createAuth(auth, gid))
                .collect(Collectors.toList());
    }

    public void removeAuths(RemoveAuthsArgs args) {
        groupService.getGroupById(args.getGroupId());

        authService.deleteAuths(args.getGroupId(), args.getAuths());
    }
}
