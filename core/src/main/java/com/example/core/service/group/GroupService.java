package com.example.core.service.group;

import com.example.core.bean.card.AuthCard;
import com.example.core.bean.card.GroupCard;
import com.example.core.bean.card.ModuleCard;
import com.example.core.bean.card.PageCard;
import com.example.core.bean.db.Auth;
import com.example.core.bean.db.Group;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.GroupRepository;
import com.example.core.service.PageService;
import com.example.core.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * author：  HyZhan
 * create：  2019/4/18
 * desc：    TODO
 */
@Service
public class GroupService extends PageService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    AuthService authService;

    public Group findById(Integer groupId) {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (!groupOptional.isPresent()) {
            throw new BaseException(ErrorCode.GROUP_ERROR);
        }

        return groupOptional.get();
    }

    public PageCard<GroupCard> getAdminGroups(Pageable pageable) {

        Page<Group> groupPage = groupRepository.findAll(pageable);

        Page<GroupCard> groupCardPage = groupPage.map(group -> {

            List<Auth> authList = authService.getAuthsByGroupId(group.getId());

            // 按模块分组
            Map<String, List<AuthCard>> groupMap = authList.stream()
                    .map(AuthCard::new)
                    .collect(Collectors.groupingBy(AuthCard::getModule));

            List<ModuleCard> moduleCards = new ArrayList<>();
            groupMap.forEach((module, authCards) -> moduleCards.add(new ModuleCard(module, authCards)));

            return new GroupCard(group, moduleCards);
        });

        return convertPageCard(groupCardPage);
    }
}
