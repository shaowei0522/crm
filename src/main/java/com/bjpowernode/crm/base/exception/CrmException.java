package com.bjpowernode.crm.base.exception;

import com.bjpowernode.crm.base.constants.ExceptionEnum;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.exception
 * @Description: 自定义异常
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 20:06
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class CrmException extends RuntimeException {

    private ExceptionEnum exceptionEnum;


    public CrmException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.exceptionEnum = exceptionEnum;
    }
}
