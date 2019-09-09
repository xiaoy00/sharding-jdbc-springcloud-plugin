package com.xin.sharding.service;

import com.xin.sharding.model.ShardingSourceBaseMapping;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/18 16:25
 */
public interface ShardingSourceBaseMappingService {


    List<ShardingSourceBaseMapping> selectByDataSourceId(Integer dataSourceId);

    int update(ShardingSourceBaseMapping mapping);

    int insert(ShardingSourceBaseMapping mapping);

    int deleteByDataSourceId(Integer dataSourceId);


    int batch(List<ShardingSourceBaseMapping> mappingList,Integer dataSourceId);
}
