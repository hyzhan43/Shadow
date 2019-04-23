package com.example.core.exception.code;

/**
 * author：  HyZhan
 * create：  2018/11/7 18:01
 * desc：    错误 枚举类
 */
public enum ErrorCode {
    UNKNOWN_ERROR(-1, "服务内部错误，不想告诉你~"),
    HTTP_METHOD_ERROR(-2, "Http 请求方法不匹配!"),
    PARAMETER(-3, "参数错误"),
    USER_ERROR(-4, "用户异常"),

    // 权限
    TOKEN_EMPTY(1000, "token 无效"),
    TOKEN_ERROR(1001, "token 已过期或无效token"),
    ADMIN_ERROR(1003, "只有超级管理员可操作"),
    ACTIVE_ERROR(1004, "您目前处于未激活状态，请联系超级管理员"),
    GROUP_EMPTY(1005, "您还不属于任何权限组，请联系超级管理员获得权限"),
    AUTH_ERROR(1006, "权限不够，请联系超级管理员获得权限"),
    AUTH_EMPTY(1007, "找不到对应权限"),
    GROUP_ERROR(1008, "找不到所属权限组"),
    USER_IS_FORBID(1009, "当前用户已处于禁止状态"),
    USER_IS_ACTIVE(1010, "当前用户已处于激活状态"),
    GROUPS_EMPTY(1011, "不存在任何权限组"),


    USER_NOT_EXIST(2000, "用户不存在"),
    USER_EXIST(2001, "用户已存在"),
    ACCOUNT_OR_PASSWORD_ERROR(2002, "账号或密码错误"),
    NICKNAME_EXIST(2003, "用户名重复, 请重新输入"),
    EMAIL_EXIST(2004, "邮箱重复, 请重新输入"),
    CONFIRM_PASSWORD_ERROR(2005, "两次密码输入不正确, 请重新输入");

    private Integer code;
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
