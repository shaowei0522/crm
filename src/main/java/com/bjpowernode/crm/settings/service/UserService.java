package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.bean.User;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.settings.service
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 19:39
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface UserService {
    User login(User user);

    List<User> queryAllUsers();
}
