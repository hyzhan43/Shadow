package com.zhan.core.bean.card;

import com.zhan.core.bean.db.Log;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * author：  HyZhan
 * create：  2019/4/24
 * desc：    TODO
 */
@Data
public class LogCard {

    private Integer id;

    /**
     * 日志信息
     */
    private String message;

    private Integer userId;

    private String username;

    /**
     * 请求的 http返回码
     */
    private Integer statusCode;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 访问那个权限
     */
    private String authority;

    /**
     * 日志创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public LogCard(Log log) {
        BeanUtils.copyProperties(log, this);
    }
}
