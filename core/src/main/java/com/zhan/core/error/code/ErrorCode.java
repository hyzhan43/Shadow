package com.zhan.core.error.code;

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
    GROUP_IS_EXIST(1012, "分组已存在，不可创建同名分组"),
    GROUP_EXIST_USER(1013, "分组下存在用户，不可删除"),
    AUTH_IS_EXIST(1014, "已有权限，不可重复添加"),
    ROUTE_META_NOT_EXIST(1015, "找不到对应的权限"),
    REFRESH_ERROR(1016, "刷新token 失败"),
    USER_NOT_EXIST(1017, "用户不存在"),
    USER_EXIST(1018, "用户已存在"),
    ACCOUNT_OR_PASSWORD_ERROR(1019, "账号或密码错误"),
    NICKNAME_EXIST(1020, "用户名重复, 请重新输入"),
    EMAIL_EXIST(1021, "邮箱重复, 请重新输入"),
    CONFIRM_PASSWORD_ERROR(1022, "两次密码输入不正确, 请重新输入"),
    SEARCH_KEYWORD_EMPTY(1023, "搜索关键字不可为空"),

    ;
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
