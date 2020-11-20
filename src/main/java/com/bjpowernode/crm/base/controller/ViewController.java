package com.bjpowernode.crm.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.controller
 * @Description: baseController，进行页面跳转，既有支持restful风格的controller，也有普通的controller
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 20:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ViewController {
    /**
     * 支持restful风格的视图跳转controller，可以进行内部资源的访问
     * */
    @RequestMapping({"/toView/{modelName}/{viewName}","/toView/{viewName}"})
    public String toView(@PathVariable(value = "modelName",required = false) String modelName,
                         @PathVariable("viewName") String viewName){

        if (modelName != null && modelName != "") {
            return modelName + File.separator + viewName;
        }
        return viewName;
    }

    /**
     * 内部跳转，只需要提供相对于视图解析器配置的路径的相对路径，即可进行跳转，
     * 因为外部无法访问WEB-INF下面的资源文件
     * */
    @RequestMapping("/innerForward")
    public String innerForward(String path){
        return path;
    }
}
