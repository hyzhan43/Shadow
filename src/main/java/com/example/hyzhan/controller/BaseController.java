package com.example.hyzhan.controller;

import com.example.hyzhan.bean.args.BaseArgs;
import com.example.hyzhan.exception.BaseException;
import com.example.hyzhan.exception.code.ErrorCode;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
public class BaseController {

    /**
     *  校验页码
     */
    protected void checkPaginate(BaseArgs args){
        Integer page = args.getPage();
        Integer pageSize = args.getPageSize();

        if (page < 0 || pageSize < 0){
            throw new BaseException(ErrorCode.PARAMETER);
        }
    }
}
