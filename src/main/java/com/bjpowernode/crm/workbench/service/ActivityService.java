package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.ActivityQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/17 17:22
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityService {
    List<Map<String, String>> queryAll(ActivityQueryVo activityQueryVo);

    void saveActivity(Activity activity);

    Activity queryActivityByPrimary(Activity activity);

    void updateActivity(Activity activity);

    void deleteActivities(String ids);

    Activity queryActivityDetailById(String id);
}
