package com.wzxy.uavfilingsystem.common;

public enum ResultCode {

    SUCCESS("操作成功"),
    USER_LOGIN_ERROR("用户登录失败"),
    SYSTEM_INNER_ERROR("系统内部错误"),
    TOKEN_EXPIRED("令牌过期"),
    TOKEN_INVALID("令牌无效");

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
