package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.settings.bean.User;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/25 15:57
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface TransactionService {
    List<User> queryAllUser();

    List<String> queryCutomers(String name);
}
