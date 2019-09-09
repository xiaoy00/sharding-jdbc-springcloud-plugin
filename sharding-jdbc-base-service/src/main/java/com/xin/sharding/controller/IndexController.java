package com.xin.sharding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/11 11:02
 */
@Controller
public class IndexController {

    @RequestMapping("project")
    public String project(){
        return "project";
    }

/*    @RequestMapping("dataSource")
    public String dataSource(){
        return "dataSource";
    }*/

//    @RequestMapping("project/index")
//    public String project(){
//        return "project/index";
//    }


}
