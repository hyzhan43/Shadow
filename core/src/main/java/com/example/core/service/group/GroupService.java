package com.example.core.service.group;

import com.example.core.bean.db.Group;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/18
 * desc：    TODO
 */
@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Group findById(Integer groupId) {

        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (!groupOptional.isPresent()) {
            throw new BaseException(ErrorCode.GROUP_ERROR);
        }

        return groupOptional.get();
    }
}
