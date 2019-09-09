package com.xin.sharding.controller;

import com.car.resume.util.RedisUtil;
import com.xin.sharding.model.ShardingConfig;
import com.xin.sharding.service.ShardingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/19 15:32
 */
@RestController
public class ShardingController {
    private static Logger logger = LogManager.getLogger(ShardingController.class);
    @Autowired
    ShardingService shardingService;

    @Autowired
    RedisUtil redisUtils;

    private final long hour = 60*60;

    @RequestMapping("getShardingConfig")
    public ShardingConfig getShardingConfig(String name){
        Object object = redisUtils.getObject("sharding:jdbc:config:"+name);
        if(object != null){
            return (ShardingConfig) object;
        }
        logger.info("sharding",object);
        ShardingConfig shardingConfig = shardingService.getShardingConfig(name);
        redisUtils.set("sharding:jdbc:config:" + name,shardingConfig, hour);
        return shardingConfig;
    }

    @RequestMapping("clearShardingConfig")
    public String clearShardingConfig(String name){
        redisUtils.remove("sharding:jdbc:config:"+name);
        Object object = redisUtils.getObject("sharding:jdbc:config:"+name);
        if (object == null){
            return  "删除成功";
        }
        return "clear失败";
    }



}
