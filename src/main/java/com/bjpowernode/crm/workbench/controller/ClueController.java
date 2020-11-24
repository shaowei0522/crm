package com.bjpowernode.crm.workbench.controller;

import com.bjpowernode.crm.base.bean.ResultVo;
import com.bjpowernode.crm.base.constants.CrmConstants;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.workbench.bean.Clue;
import com.bjpowernode.crm.workbench.bean.ClueRemark;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.controller
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 21:18
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ClueController {
    @Autowired
    private ClueService clueService;
    /**
     * 查询所有的线索，进行分页显示
     * 同时也支持动态SQL，进行条件查询
     * 此处不仅需要返回查询结果，还需要返回分页信息，使用前端分页插件进行分页展示
     * 因为pageHelper已经封装好了pageInfo所以返回一个PageInfo对象即可，该对象中都有相关属性及信息
     * */
    @RequestMapping("/workbench/clue/queryAllClues")
    @ResponseBody
    public PageInfo queryAllClues(@RequestParam(required = false,defaultValue = "1") int page,
                                    @RequestParam(required = false,defaultValue = "2") int pageSize){
        PageHelper.startPage(page,pageSize);
        List<Clue> clueList = clueService.queryAllClues();
        PageInfo<Clue> pageInfo = new PageInfo<>(clueList);
        return pageInfo;
    }
    /**
     * 添加线索
     * */
    @RequestMapping("/workbench/addClue")
    @ResponseBody
    public ResultVo addClue(Clue clue, HttpSession session){
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
        clue.setCreateBy(user.getName());
        try {
            clueService.addClue(clue);
            resultVo.setOK(true);
            resultVo.setMsg("添加成功！");
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
        }
        return resultVo;
    }
    /**
     * 一次性查询出所有的clue相关的数据，进入详情页面，进行数据的展示
     * 根据clue的id进行数据的查询
     * */
    @RequestMapping("/workbench/clue/queryClueDetail")
    public String queryClueDetail(String id,HttpSession httpSession){
        Clue clue = clueService.queryClueDetail(id);
        httpSession.setAttribute("clue",clue);

        return "clue/detail";
    }
    /**
     * 删除线索备注，根据id主键进行删除
     * */
    @RequestMapping("workbench/clue/deleteClueRemark")
    @ResponseBody
    public ResultVo deleteClueRemark(String id){
        ResultVo resultVo = new ResultVo();
        try {
            clueService.deleteClueRemark(id);
            resultVo.setOK(true);
            resultVo.setMsg("删除线索备注成功！");
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
        }
        return resultVo;
    }
    /**
     * 修改备注信息
     * */
    @RequestMapping("workbench/clue/updateClueRemark")
    @ResponseBody
    public ResultVo updateClueRemark(ClueRemark clueRemark,HttpSession httpSession){
        User user = (User) httpSession.getAttribute(CrmConstants.LOGIN_USER);
        clueRemark.setEditBy(user.getName());

        ResultVo resultVo = new ResultVo();
        try {
            clueService.updateClueRemark(clueRemark);
            resultVo.setOK(true);
            resultVo.setMsg("修改备注信息成功！");
        } catch (Exception e) {
            resultVo.setMsg(e.getMessage());
        }
        return resultVo;
    }

}
