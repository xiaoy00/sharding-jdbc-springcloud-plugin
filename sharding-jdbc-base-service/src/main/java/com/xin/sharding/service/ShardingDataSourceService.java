package com.xin.sharding.service;

import com.xin.sharding.model.ShardingDataSourceInfo;
import com.xin.sharding.model.vo.ShardingDataSourceVO;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/12 15:22
 */
public interface ShardingDataSourceService {


    List<ShardingDataSourceVO> getAllByProjectId(Integer projectId);

    ShardingDataSourceVO getById(Integer id);

    ShardingDataSourceInfo select(Integer id);

    int update(ShardingDataSourceVO shardingProject);

    int insert(ShardingDataSourceVO shardingProject);

    int delete(Integer id);

}
