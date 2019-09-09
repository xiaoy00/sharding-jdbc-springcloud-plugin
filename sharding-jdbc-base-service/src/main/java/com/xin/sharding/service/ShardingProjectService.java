package com.xin.sharding.service;

import com.xin.sharding.model.ShardingProject;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/11 12:32
 */
public interface ShardingProjectService {

     List<ShardingProject> getAll();

     ShardingProject select(Integer id);

     int update(ShardingProject shardingProject);

     int insert(ShardingProject shardingProject);

     int delete(Integer id);

}
