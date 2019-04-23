package com.example.core.service;

import com.example.core.bean.card.AuthCard;
import com.example.core.bean.card.GroupCard;
import com.example.core.bean.card.ModuleCard;
import com.example.core.bean.card.PageCard;
import com.example.core.bean.db.Auth;
import com.example.core.bean.db.Group;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.GroupRepository;
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
public class GroupService extends PageResource {

    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public Group getGroupById(Integer groupId) {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (!groupOptional.isPresent()) {
            throw new BaseException(ErrorCode.GROUP_ERROR);
        }

        return groupOptional.get();
    }

    public Page<Group> getAllGroup(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }
}
