package com.xin.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/17 17:11
 */
@EnableScheduling//支持定时任务
@SpringCloudApplication
@MapperScan("com.xin.sharding.mapper")
@ComponentScan({"com.car.resume.config","com.car.resume.util","com.xin.sharding.*"})
public class ShardingApplication {

    public static void main(String[] args){
        SpringApplication.run(ShardingApplication.class, args);
    }

}
