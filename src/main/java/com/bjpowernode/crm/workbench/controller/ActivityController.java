package com.bjpowernode.crm.workbench.controller;

import com.bjpowernode.crm.base.bean.PaginationVo;
import com.bjpowernode.crm.base.bean.ResultVo;
import com.bjpowernode.crm.base.constants.CrmConstants;
import com.bjpowernode.crm.base.util.DateTimeUtil;
import com.bjpowernode.crm.base.util.UUIDUtil;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.ActivityQueryVo;
import com.bjpowernode.crm.workbench.bean.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

/*此处可以考虑使用restController*/
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    /**
     * 查询活动信息，可以根据条件进行查询，分页查询也可以实现
     * */
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
    /**
     * 查询所有的User
     * */
    @RequestMapping("/workbench/activity/queryAllUsers")
    @ResponseBody
    public Object queryAllUsers(){
        List<User> userList = userService.queryAllUsers();
        return userList;
    }
    /**
     * 保存添加的活动信息
     * */
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

    @RequestMapping("/workbench/queryActivityByPrimary")
    @ResponseBody
    public Object queryActivityByPrimary(Activity activity){
//        首先查询所有的用户
        List<User> userList = userService.queryAllUsers();
//        其次，查询选中的要修改的活动
        activity = activityService.queryActivityByPrimary(activity);

//        此处可以考虑使用Map进行数据封装
        Map<String,Object> map = new HashMap<>();
        map.put("users", userList);
        map.put("activity", activity);

        return map;
    }

    @RequestMapping("/workbench/updateActivity")
    @ResponseBody
    public ResultVo updateActivity(Activity activity){
        ResultVo resultVo = new ResultVo();
        try {
            activityService.updateActivity(activity);
            resultVo.setMsg("数据修改成功！");
            return resultVo;
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
    }
    @RequestMapping("/workbench/deleteActivities")
    @ResponseBody
    public ResultVo deleteActivities(String ids){
        ResultVo resultVo = new ResultVo();
        try {
            activityService.deleteActivities(ids);
            resultVo.setOK(true);
            resultVo.setMsg("删除数据成功！");
            return resultVo;
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
    }

    /**
     * 点击超链接后进入该请求，查询市场活动的细节
     * */
    @RequestMapping("/workbench/queryActivityDetailById")
    public String queryActivityDetailById(String id,HttpSession httpSession){
        Activity activity = activityService.queryActivityDetailById(id);

        httpSession.setAttribute("activity", activity);
        return "forward:/toView/activity/detail";
    }

    /**
     * 修改活动备注
     * */
    @RequestMapping("/workbench/updateActivityRemark")
    @ResponseBody
    public ResultVo updateActivityRemark(ActivityRemark activityRemark,HttpSession session){
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
        if (user != null) {
            activityRemark.setEditBy(user.getName());
        }
        try {
            activityService.updateActivityRemark(activityRemark);
            resultVo.setOK(true);
            resultVo.setMsg("修改成功");
            return resultVo;
        } catch (Exception e) {

            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
    }

    /**
     * 删除活动备注
     */
    @RequestMapping("/workbench/deleteActivityRemarkByPrimaryKey")
    @ResponseBody
    public ResultVo deleteActivityRemarkByPrimaryKey(ActivityRemark activityRemark) {
        ResultVo resultVo = new ResultVo();
        try {
            activityService.deleteActivityRemarkByPrimaryKey(activityRemark);
            resultVo.setOK(true);
            resultVo.setMsg("删除成功！");
            return resultVo;
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
    }

    /**
     * 添加备注
     * */
    @RequestMapping("/workbench/addActivityRemark")
    @ResponseBody
    public Map<String,Object> addActivityRemark(ActivityRemark activityRemark,HttpSession httpSession){
        Map<String,Object> stringObjectMap = new HashMap<>();
        ResultVo resultVo = new ResultVo();
        User user = (User) httpSession.getAttribute(CrmConstants.LOGIN_USER);
        activityRemark.setCreateBy(user.getName());
        activityRemark.setEditBy(user.getName());
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setEditFlag("0");
        stringObjectMap.put("activityRemark", activityRemark);
        try {
            activityService.addActivityRemark(activityRemark);
            resultVo.setOK(true);
            resultVo.setMsg("添加成功！");
            stringObjectMap.put("resultVo", resultVo);
            return stringObjectMap;
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
            stringObjectMap.put("resultVo", resultVo);
            return stringObjectMap;
        }
    }

    /**
     * 删除整个活动，包括备注信息
     * */
    @RequestMapping("/workbench/deleteActivityDetail")
    public String deleteActivityDetail(String activityId){
        try {
            activityService.deleteActivityDetail(activityId);
            return "forward:/toView/activity/index";
        } catch (Exception e) {
            return "../../error";
        }




    }
}
