package com.xin.sharding.mapper;

import com.xin.sharding.model.ShardingGroup;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShardingGroupMapper {


    int deleteByPrimaryKey(Integer groupId);

    int insert(ShardingGroup record);


    int insertSelective(ShardingGroup record);


    ShardingGroup selectByPrimaryKey(Integer groupId);

    /**
     * 根据项目获取分组信息
     * @param logicTableId
     * @return
     */
    List<ShardingGroup> selectByLogicTableId(Integer logicTableId);

    int updateByPrimaryKeySelective(ShardingGroup record);

    int updateByPrimaryKey(ShardingGroup record);

    @Update("update sharding_group set status = 0 where logic_table_id = #{logicTableId}")
    void updateOldGroup(Integer logicTableId);
}