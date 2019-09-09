package com.xin.sharding.service.impl;

import com.xin.sharding.mapper.ShardingSourceBaseMappingMapper;
import com.xin.sharding.model.ShardingSourceBaseMapping;
import com.xin.sharding.service.ShardingSourceBaseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/18 16:29
 */
@Service
public class ShardingSourceBaseMappingServiceImpl implements ShardingSourceBaseMappingService {

    @Autowired
    ShardingSourceBaseMappingMapper shardingSourceBaseMappingMapper;
    @Override
    public List<ShardingSourceBaseMapping> selectByDataSourceId(Integer dataSourceId) {
        return shardingSourceBaseMappingMapper.selectByDataSourceId(dataSourceId);
    }

    @Override
    public int batch(List<ShardingSourceBaseMapping> mappingList,Integer dataSourceId) {
        int i = deleteByDataSourceId(dataSourceId);
        Long count = mappingList.stream().map(m ->
                insert(m)
        ).filter(num -> num == 0).count();
        return count.intValue();
    }


    @Override
    public int update(ShardingSourceBaseMapping mapping) {

        return shardingSourceBaseMappingMapper.update(mapping);
    }

    @Override
    public int insert(ShardingSourceBaseMapping mapping) {
        return shardingSourceBaseMappingMapper.insert(mapping);
    }

    @Override
    public int deleteByDataSourceId(Integer dataSourceId) {
        return shardingSourceBaseMappingMapper.deleteByDataSourceId(dataSourceId);
    }


}
