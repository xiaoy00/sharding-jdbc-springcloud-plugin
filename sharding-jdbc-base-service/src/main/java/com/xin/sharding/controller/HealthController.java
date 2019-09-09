package com.xin.sharding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: songxiaoyue
 * @Date: 2019/5/15 18:02
 */
@RestController
public class HealthController {
    @RequestMapping("health")
    public Object health(){
        return "";
    }

}
