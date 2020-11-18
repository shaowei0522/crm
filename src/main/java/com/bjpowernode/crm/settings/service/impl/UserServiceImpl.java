package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.base.constants.ExceptionEnum;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.base.util.DateTimeUtil;
import com.bjpowernode.crm.base.util.MD5Util;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.settings.service.impl
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 19:40
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {

//        获取用户输入的密码
        String loginPwd = user.getLoginPwd();

//        进行用户密码的加密
        String md5 = MD5Util.getMD5(loginPwd);
        user.setLoginPwd(md5);

//        取出controller层获取的IP地址，用来IP对比
        String allowIps = user.getAllowIps();

//        对用户信息进行更正，IP只是作为参数暂时传递进来的
        user.setAllowIps(null);

//        此处查询用户信息
        user = userMapper.selectOne(user);

        if (user != null) {
//            此处说明用户存在
            if (!"0".equals(user.getLockState())) {
//               此处说明用户账号没有被禁用
                if (DateTimeUtil.getSysTime().compareTo(user.getExpireTime()) < 0) {
//                    此处说明用户的账号没有过期
                    if (user.getAllowIps() != null && user.getAllowIps() != "") {
                        if (user.getAllowIps().contains(allowIps)) {
                        } else {
                            throw new CrmException(ExceptionEnum.LOGIN_ACCOUNT_IP);
                        }
                    }
                } else {
                    throw new CrmException(ExceptionEnum.LOGIN_ACCOUNT_EXPIRE);
                }
            } else {
                throw new CrmException(ExceptionEnum.LOGIN_ACCOUNT_FORBID);
            }
        } else {
//            抛出用户不存在或者密码错误异常
            throw new CrmException(ExceptionEnum.LOGIN_ACCOUNT_ERROR);
        }
        return user;
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAll();
    }
}
