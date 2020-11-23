package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.base.bean.DictionaryType;
import com.bjpowernode.crm.base.bean.DictionaryValue;
import com.bjpowernode.crm.settings.mapper.DictionaryTypeMapper;
import com.bjpowernode.crm.settings.mapper.DictionaryValueMapper;
import com.bjpowernode.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.settings.service.impl
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 19:37
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Autowired
    private DictionaryValueMapper dictionaryValueMapper;

    @Override
    public List<DictionaryType> queryDictionary() {
        List<DictionaryType> dictionaryTypeList = dictionaryTypeMapper.selectAll();
        for (DictionaryType dictionaryType : dictionaryTypeList) {
//          根据外键进行查询dictionaryValue，然后封装到dictionaryType中去
            DictionaryValue dictionaryValue = new DictionaryValue();
            dictionaryValue.setTypeCode(dictionaryType.getCode());

            List<DictionaryValue> select = dictionaryValueMapper.select(dictionaryValue);
            dictionaryType.setDictionaryValueList(select);
        }
        return dictionaryTypeList;
    }
}
