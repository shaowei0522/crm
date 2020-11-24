package com.bjpowernode.crm.base.controller;

import com.bjpowernode.crm.base.bean.DictionaryType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.controller
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 19:59
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class DictionaryController {

    @RequestMapping("/dictionaryCache")
    @ResponseBody
    public Object cache(HttpSession session){
        List<DictionaryType> dictionaryType = (List<DictionaryType>) session.getServletContext().getAttribute("dictionaryType");

        return dictionaryType;
    }
}
