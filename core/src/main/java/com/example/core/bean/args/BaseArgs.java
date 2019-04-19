package com.example.core.bean.args;

import com.example.core.config.Setting;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/14
 * desc：    TODO
 */
@Data
public class BaseArgs {

    public Integer page = Setting.PAGE;

    public Integer pageSize = Setting.PAGE_SIZE;
}
