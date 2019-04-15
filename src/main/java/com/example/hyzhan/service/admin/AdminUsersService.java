package com.example.hyzhan.service.admin;

import com.example.hyzhan.bean.args.AdminUserArgs;
import com.example.hyzhan.bean.card.AdminUserCard;
import com.example.hyzhan.bean.card.PageCard;
import com.example.hyzhan.bean.db.cms.User;
import com.example.hyzhan.bean.model.AdminUsersModel;
import com.example.hyzhan.repository.UsersRepository;
import com.example.hyzhan.service.PageService;
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
    UsersRepository usersRepository;

    public PageCard getAdminUsers(AdminUserArgs args) {

        Integer groupId = args.getGroupId();
        Integer admin = AdminUsersModel.COMMON;

        // 分页查询条件
        Pageable pageable = PageRequest.of(args.getPage(), args.getPageSize());

        Page<User> userPage;
        if (groupId == null) {
            userPage = usersRepository.findByAdmin(admin, pageable);
        } else {
            userPage = usersRepository.findByAdminAndGroupId(admin, groupId, pageable);
        }

        Page<AdminUserCard> page = userPage.map(AdminUserCard::new);

        return convertPageCard(page);
    }
}
