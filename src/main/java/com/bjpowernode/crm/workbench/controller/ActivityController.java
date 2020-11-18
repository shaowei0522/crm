package com.bjpowernode.crm.workbench.controller;

import com.bjpowernode.crm.base.bean.PaginationVo;
import com.bjpowernode.crm.base.bean.ResultVo;
import com.bjpowernode.crm.base.constants.CrmConstants;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.base.util.DateTimeUtil;
import com.bjpowernode.crm.base.util.UUIDUtil;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.ActivityQueryVo;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.controller
 * @Description: 此处多使用Ajax进行异步请求，所以返回类型多为object
 * @Author: 王少伟
 * @CreateDate: 2020/11/17 17:19
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @RequestMapping("/workbench/activity/queryActivity")
    public @ResponseBody PaginationVo
    queryActivity(@RequestParam(defaultValue = "1",required = false) int page,
                  @RequestParam(defaultValue = "2",required = false) int pageSize,
                  ActivityQueryVo activityQueryVo){

//        开启分页功能
        PageHelper.startPage(page, pageSize);

        List<Map<String,String>> activityList = activityService.queryAll(activityQueryVo);


        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(activityList);

//        因为返回的数据要包含分页相关的信息，所以要创建一个自定义的pojo进行查询数据的封装，然后进行返回数据

        PaginationVo paginationVo = new PaginationVo(pageInfo);
        System.out.println(paginationVo);

        return paginationVo;
    }


    @RequestMapping("/workbench/activity/queryAllUsers")
    @ResponseBody
    public Object queryAllUsers(){
        List<User> userList = userService.queryAllUsers();
        return userList;
    }



    @RequestMapping("/workbench/activity/saveActivity")
    @ResponseBody
    public Object saveActivity(Activity activity, HttpSession session){
//        此处不需要对owner进行处理，因为返回过来的就是用户的uuid
        User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
        activity.setCreateBy(user.getName());
        activity.setEditBy(user.getName());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        activity.setEditTime(DateTimeUtil.getSysTime());
        activity.setId(UUIDUtil.getUUID());
        ResultVo resultVo = new ResultVo();
        try {
            activityService.saveActivity(activity);
            resultVo.setOK(true);
            resultVo.setMsg("添加成功");
        } catch (Exception e) {
            resultVo.setOK(false);
            resultVo.setMsg(e.getMessage());
        }

//        将反馈信息返回至前台
        return resultVo;
    }
}
