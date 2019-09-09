package com.xin.sharding;

import io.shardingsphere.core.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/21 11:49
 */
public class QuartzService {

    @Scheduled(cron = "0 0 17 * * ?")
    public void timerToNow(){
         System.out.println("更新数据源: now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //shardingDataSource.close();
        shardingConfigurationFactory.getShardingConfiguration();
    }

    @Autowired
    ShardingConfigurationFactory shardingConfigurationFactory;
    @Resource
    ShardingDataSource shardingDataSource;

}