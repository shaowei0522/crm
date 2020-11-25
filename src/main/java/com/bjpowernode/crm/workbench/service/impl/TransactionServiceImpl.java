package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.workbench.bean.Customer;
import com.bjpowernode.crm.workbench.mapper.CustomerMapper;
import com.bjpowernode.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/25 15:58
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<String> queryCutomers(String name) {
        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name","%" + name + "%");
        List<Customer> customers = customerMapper.selectByExample(example);
        List<String> stringList = new ArrayList<>();
        for (Customer customer : customers) {
            stringList.add(customer.getName());
        }
        return stringList;
    }
}
