package com.xin.sharding.algorithm;

import io.shardingsphere.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;
import java.util.Random;

/**
 * @Author: songxiaoyue
 * @Date: 2018/8/7 18:19
 * 从库负载算法
 */
public class MyMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {
    public MyMasterSlaveLoadBalanceAlgorithm() {
    }

    @Override
    public String getDataSource(String s, String s1, List<String> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
