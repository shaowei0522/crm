package com.bjpowernode.crm.workbench.controller;

import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.controller
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/25 15:55
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("workbench/transaction/queryAllUser")
    @ResponseBody
    public List<User> queryAllUser(){
        return transactionService.queryAllUser();
    }

    @RequestMapping("workbench/transaction/customer")
    @ResponseBody
    public List<String> customer(String name){
        return transactionService.queryCutomers(name);
    }

    @RequestMapping("workbench/transaction/stage2Possibilities")
    @ResponseBody
    public Object stage2Possibilities(String stage, HttpSession httpSession){
        Map<String,String> stage2PossiblitiesMap = (Map<String, String>) httpSession.getServletContext().getAttribute("stage2PossiblitiesMap");
        String s = stage2PossiblitiesMap.get(stage);

        return s;
    }
}
