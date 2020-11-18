package com.bjpowernode.crm.base.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.bean
 * @Description: 此处对分页查询的数据以及分页相关的信息进行封装，主要使用PageInfo 的api方法进行相关数据的赋值
 * @Author: 王少伟
 * @CreateDate: 2020/11/17 19:58
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class PaginationVo<T> {
//    当前页码
    private int page;
//    每页的数据个数
    private int pageSize;
//    总页数
    private int pages;
//    总记录数
    private long total;
//    每页的数据
    private List<T> pageInfoList;

    private PageInfo<T> pageInfo;

    public PaginationVo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
        page = pageInfo.getPageNum();
        pageSize = pageInfo.getSize();
        pages = pageInfo.getPages();
        total = pageInfo.getTotal();
        pageInfoList = pageInfo.getList();
    }

    @Override
    public String toString() {
        return "PaginationVo{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", total=" + total +
                ", pageInfoList=" + pageInfoList +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getPageInfoList() {
        return pageInfoList;
    }

    public void setPageInfoList(List<T> pageInfoList) {
        this.pageInfoList = pageInfoList;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
