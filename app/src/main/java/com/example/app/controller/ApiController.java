package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author：  HyZhan
 * create：  2019/4/15
 * desc：    TODO
 */
@Controller
public class ApiController {

    @RequestMapping("/doc")
    public String api(){
        return "/index";
    }
}
