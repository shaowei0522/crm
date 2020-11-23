package com.bjpowernode.crm.base.bean;

import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.bean
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 19:24
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Table(name = "tbl_dic_type")
@NameStyle
public class DictionaryType {
    @Id
    private String code;
    private String name;
    private String description;

    private List<DictionaryValue> dictionaryValueList;

    public List<DictionaryValue> getDictionaryValueList() {
        return dictionaryValueList;
    }

    public void setDictionaryValueList(List<DictionaryValue> dictionaryValueList) {
        this.dictionaryValueList = dictionaryValueList;
    }

    @Override
    public String toString() {
        return "DictionaryType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dictionaryValueList=" + dictionaryValueList +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
