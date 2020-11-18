package com.bjpowernode.crm.base.constants;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.constants
 * @Description: 用户登录信息的枚举
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 20:01
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public enum ExceptionEnum {
    LOGIN_ACCOUNT_ERROR("001","用户名或者密码错误"),
    LOGIN_ACCOUNT_EXPIRE("001","账号已经失效"),
    LOGIN_ACCOUNT_FORBID("001","账号被禁用"),
    LOGIN_ACCOUNT_IP("001", "非法的ip"),
    ACTIVITY_SAVE("002","添加市场活动失败");

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    @Override
    public String toString() {
        return "ExceptionEnum{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
