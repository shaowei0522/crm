package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.ActivityQueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.mapper
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/17 19:08
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityMapper extends Mapper<Activity> {
    List<Map<String, String>> queryAll(ActivityQueryVo activityQueryVo);
}
