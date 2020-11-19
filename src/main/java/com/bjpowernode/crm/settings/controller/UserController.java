package com.bjpowernode.crm.settings.controller;

import com.bjpowernode.crm.base.constants.CrmConstants;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.settings.controller
 * @Description: controller 层主要还是用来接收和发送请求的，不要用来处理具体的业务逻辑，这些代码需要放在service层进行整理
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 19:39
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/settings/user/login")
    public String login(HttpServletRequest request, User user, Model model, HttpSession session){
        String remoteAddr = request.getRemoteAddr();
        user.setAllowIps(remoteAddr);
        try {
            user = userService.login(user);
//            model.addAttribute(CrmConstants.LOGIN_USER, user);
            session.setAttribute(CrmConstants.LOGIN_USER,user);
            return "index";
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute(CrmConstants.LOGIN_MSG, message);
            return "../../login";
        }
    }

    /**
     * 用户退出
     * */
    @RequestMapping("/settings/user/logout")
    public String logout(HttpSession session){
        session.removeAttribute(CrmConstants.LOGIN_USER);
        return "../../login";

    }
}
