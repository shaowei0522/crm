package com.bjpowernode.crm.base.bean;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.bean
 * @Description: 专门给客户返回消息的类
 * @Author: 王少伟
 * @CreateDate: 2020/11/18 09:46
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ResultVo {

    private boolean isOK;

    private String msg;

    @Override
    public String toString() {
        return "ResultVo{" +
                "isOK=" + isOK +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
