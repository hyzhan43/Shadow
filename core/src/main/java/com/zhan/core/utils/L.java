package com.zhan.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
public class L {

    private static Logger logger = LoggerFactory.getLogger(L.class);

    public static void i(String msg){
        logger.info(msg);
    }

    public static void error(String msg){
        logger.error(msg);
    }
}
