package com.bjpowernode.crm.base.cache;

import com.bjpowernode.crm.base.bean.DictionaryType;
import com.bjpowernode.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.cache
 * @Description: 数据词典的初始化controller，服务器启动的时候会进行数据词典的查询
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 19:29
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Component
public class CrmCache {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private DictionaryService dictionaryService;

    @PostConstruct
    public void crmCache(){
        List<DictionaryType> dictionaryTypeList = dictionaryService.queryDictionary();
//        此处如果正常的话已经查询出来的数据词典
        servletContext.setAttribute("dictionaryType", dictionaryTypeList);


    }
}
