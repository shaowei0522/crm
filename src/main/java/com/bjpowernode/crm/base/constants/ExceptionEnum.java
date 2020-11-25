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
    ACTIVITY_SAVE("002","添加市场活动失败"),
    ACTIVITY_UPDATE("002", "修改市场活动失败"),
    ACTIVITY_DELETE("002","删除数据失败"),
    ACTIVITY_REMARK_UPDATE("002","修改备注信息失败"),
    ACTIVITY_REMARK_DELETE("002","删除备注信息失败"),
    ACTIVITY_REMARK_ADD("002","添加备注信息失败"),
    ACTIVITY_ALL_DELETE("002","删除市场活动以及备注失败"),
    CLUE_ADD("003","添加线索失败"),
    CLUE_REMARK_DELETE("003","删除线索备注失败"),
    CLUE_REMARK_ADD("003","添加线索备注失败"),
    CLUE_REMARK_UPDATE("003","修改线索备注失败"),
    CLUE_ACTIVITY_RELATION_DELETE("003","解除市场活动关联失败"),
    CLUE_ACTIVITY_RELATION_ADD("003","添加市场活动关联失败"),
    CUSTOMER_ADD("004","添加客户失败"),
    CONTACT_ADD("005","添加联系人失败"),
    CONTACT_REMARK_ADD("006","添加联系人备注失败"),
    CUSTOMER_REMARK_ADD("007","添加客户备注失败"),
    CLUE_CONVERT("008","线索转换失败");






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
