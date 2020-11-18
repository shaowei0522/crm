package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.base.constants.ExceptionEnum;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.ActivityQueryVo;
import com.bjpowernode.crm.workbench.mapper.ActivityMapper;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
