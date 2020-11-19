package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.base.constants.ExceptionEnum;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.ActivityQueryVo;
import com.bjpowernode.crm.workbench.bean.ActivityRemark;
import com.bjpowernode.crm.workbench.mapper.ActivityMapper;
import com.bjpowernode.crm.workbench.mapper.ActivityRemarkMapper;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/17 17:22
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;
    @Override
    public List<Map<String, String>> queryAll(ActivityQueryVo activityQueryVo) {
        return activityMapper.queryAll(activityQueryVo);
    }

    @Override
    public void saveActivity(Activity activity) {
        int i = activityMapper.insertSelective(activity);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.ACTIVITY_SAVE);
        }
    }

    @Override
    public Activity queryActivityByPrimary(Activity activity) {
        return activityMapper.selectByPrimaryKey(activity);
    }

    @Override
    public void updateActivity(Activity activity) {
        int i = activityMapper.updateByPrimaryKeySelective(activity);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.ACTIVITY_UPDATE);
        }
    }

    @Override
    public void deleteActivities(String ids) {
//        对数据进行处理
        String[] strings = ids.split(",");
        for (String string : strings) {
            int i = activityMapper.deleteByPrimaryKey(string);
            if (i == 0) {
                throw new CrmException(ExceptionEnum.ACTIVITY_DELETE);
            }
        }
    }

    /*
    * 此处查询出来的activity是owner修正过的activity，同时还要查询出来activityRemark
    * */
    @Override
    public Activity queryActivityDetailById(String id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
//        根据主键查询user
        User user = userMapper.selectByPrimaryKey(activity.getOwner());
//        将查询出来的username设置到activity中
        activity.setOwner(user.getName());
//        继续查询activityRemark，此处已知activityRemark的外键，即activity的主键，所以要借用tkMapper的Example进行查询

        Example example = new Example(ActivityRemark.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("activityId", activity.getId());
        List<ActivityRemark> activityRemarks = activityRemarkMapper.selectByExample(example);
        activity.setActivityRemarkList(activityRemarks);


        return activity;
    }

}
