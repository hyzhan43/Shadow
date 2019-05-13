package com.zhan.core.bean.card;

import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/26
 * desc：    TODO
 */
public class BaseCard {

    public boolean checkIsNullOrEmpty(Object param){
        return param == null || "".equals(param.toString().trim());
    }
}
