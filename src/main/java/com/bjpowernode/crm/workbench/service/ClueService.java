package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.bean.*;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 21:24
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ClueService {
    void addClue(Clue clue);

    List<Clue> queryAllClues();

    Clue queryClueDetail(String id);

    void deleteClueRemark(String id);

    void updateClueRemark(ClueRemark clueRemark);

    ClueRemark addClueRemark(ClueRemark clueRemark);

    void deleteBindActivity(ClueActivityRelation clueActivityRelation);

    List<Activity> selectActivitiesUnbind(ClueActivityRelation clueActivityRelation, String name);

    void addClueActivityRelation(String ids, String clueId);

    List<Activity> queryAllClueActivities(String clueId);

    Clue queryClueByPrimaryKey(String clueId);

    void saveConvert(String clueId, String username, Transaction transaction, String isCreateTransaction);

    List<Activity> selectActivitiesByClueId(String clueId, String name);
}
