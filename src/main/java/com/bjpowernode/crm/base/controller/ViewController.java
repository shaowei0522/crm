package com.bjpowernode.crm.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.controller
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 20:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ViewController {
    @RequestMapping({"/toView/{modelName}/{viewName}","/toView/{viewName}"})
    public String toView(@PathVariable(value = "modelName",required = false) String modelName,
                         @PathVariable("viewName") String viewName){

        if (modelName != null && modelName != "") {
            return modelName + File.separator + viewName;
        }
        return viewName;
    }
}
