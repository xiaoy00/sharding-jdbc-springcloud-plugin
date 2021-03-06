package com.xin.sharding.mapper;

import com.xin.sharding.model.ShardingTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShardingTableMapper {

    int deleteByPrimaryKey(Integer tableId);

    int insert(ShardingTable record);

    int insertSelective(ShardingTable record);

    ShardingTable selectByPrimaryKey(Integer tableId);

    /**
     * 按逻辑数据源查询表信息,带分组信息
     * @param groupId
     * @return
     */
    List<ShardingTable> selectByGroupId(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sharding_table
     *
     * @mbg.generated Tue Oct 09 14:13:02 CST 2018
     */
    int updateByPrimaryKeySelective(ShardingTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sharding_table
     *
     * @mbg.generated Tue Oct 09 14:13:02 CST 2018
     */
    int updateByPrimaryKey(ShardingTable record);
}