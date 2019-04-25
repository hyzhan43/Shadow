package com.example.core.service;

import com.example.core.bean.args.NewGroupArgs;
import com.example.core.bean.db.Group;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.GroupRepository;
import com.example.core.resource.PageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void findGroupByName(String name) {

        Optional<Group> groupOptional = groupRepository.findByName(name);

        if (groupOptional.isPresent()) {
            throw new BaseException(ErrorCode.GROUP_IS_EXIST);
        }
    }

    public Integer createGroup(NewGroupArgs args) {
        Group group = new Group();
        group.setName(args.getName());
        group.setInfo(args.getInfo());

        return groupRepository.save(group).getId();
    }

    public void updateGroup(Group group, String name, String info) {
        group.setName(name);

        if (info != null) {
            group.setInfo(info);
        }

        groupRepository.save(group);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }
}
