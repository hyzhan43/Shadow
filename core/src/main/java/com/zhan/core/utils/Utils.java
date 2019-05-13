package com.zhan.core.utils;

import org.springframework.util.DigestUtils;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
public class Utils {
    // str 进行 md5 加密
    public static String encode(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
