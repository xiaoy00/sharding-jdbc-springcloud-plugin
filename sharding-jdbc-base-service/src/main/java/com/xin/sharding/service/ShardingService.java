package com.xin.sharding.service;

import com.xin.sharding.model.ShardingConfig;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/19 15:36
 */
public interface ShardingService {


    ShardingConfig getShardingConfig(String projectName);


}
