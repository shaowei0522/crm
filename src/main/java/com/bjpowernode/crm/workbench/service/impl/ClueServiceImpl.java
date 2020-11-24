package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.base.constants.ExceptionEnum;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.base.util.DateTimeUtil;
import com.bjpowernode.crm.base.util.UUIDUtil;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.workbench.bean.Activity;
import com.bjpowernode.crm.workbench.bean.Clue;
import com.bjpowernode.crm.workbench.bean.ClueActivityRelation;
import com.bjpowernode.crm.workbench.bean.ClueRemark;
import com.bjpowernode.crm.workbench.mapper.ActivityMapper;
import com.bjpowernode.crm.workbench.mapper.ClueActivityRelationMapper;
import com.bjpowernode.crm.workbench.mapper.ClueMapper;
import com.bjpowernode.crm.workbench.mapper.ClueRemarkMapper;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 21:24
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("ClueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Override
    public void addClue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        int i = clueMapper.insertSelective(clue);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_ADD);
        }

    }

    @Override
    public List<Clue> queryAllClues() {
//        此处需要将clue中的owner的uuid转换成为user的实际名称
        List<Clue> clueList = clueMapper.selectAll();
        for (Clue clue : clueList) {
            User user = userMapper.selectByPrimaryKey(clue.getOwner());
            clue.setOwner(user.getName());
        }
        return clueList;
    }

    @Override
    public Clue queryClueDetail(String id) {
        Clue clue = clueMapper.selectByPrimaryKey(id);

        User user = userMapper.selectByPrimaryKey(clue.getOwner());
        clue.setOwner(user.getName());

        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setClueId(id);
        List<ClueRemark> select = clueRemarkMapper.select(clueRemark);
        clue.setClueRemarkList(select);

        List<Activity> activityList = new ArrayList<>();
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(id);
        List<ClueActivityRelation> select1 = clueActivityRelationMapper.select(clueActivityRelation);
        for (ClueActivityRelation activityRelation : select1) {
            Activity activity = activityMapper.selectByPrimaryKey(activityRelation.getActivityId());
            User user1 = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user1.getName());
            activityList.add(activity);
        }
        clue.setActivityList(activityList);

        return clue;
    }

    @Override
    public void deleteClueRemark(String id) {
        int i = clueRemarkMapper.deleteByPrimaryKey(id);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_REMARK_DELETE);
        }
    }

    @Override
    public void updateClueRemark(ClueRemark clueRemark) {

        clueRemark.setEditTime(DateTimeUtil.getSysTime());
        clueRemark.setEditFlag("1");
        int i = clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_REMARK_UPDATE);
        }

    }
}
