package com.xin.sharding.mapper;

import com.xin.sharding.model.ShardingLogicTable;

import java.util.List;

public interface ShardingLogicTableMapper {


    int deleteByPrimaryKey(Integer groupId);

    int insert(ShardingLogicTable record);


    int insertSelective(ShardingLogicTable record);


    ShardingLogicTable selectByPrimaryKey(Integer logicTableId);

    /**
     * 根据项目获取分表信息
     * @param projectId
     * @return
     */
    List<ShardingLogicTable> selectByProjectId(Integer projectId);

    int updateByPrimaryKeySelective(ShardingLogicTable record);

    int updateByPrimaryKey(ShardingLogicTable record);
}