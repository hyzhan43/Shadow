package com.example.core.resource;

import com.example.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author：  HyZhan
 * create：  2019/4/23
 * desc：    TODO
 */
@Component
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }
}
