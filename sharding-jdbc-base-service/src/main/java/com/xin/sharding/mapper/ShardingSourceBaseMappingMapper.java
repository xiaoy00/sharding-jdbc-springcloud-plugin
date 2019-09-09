package com.xin.sharding.mapper;

import com.xin.sharding.model.ShardingSourceBaseMapping;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/18 16:36
 */
@Mapper
public interface ShardingSourceBaseMappingMapper {


    int update(ShardingSourceBaseMapping mapping);

    @Insert("insert into sharding_source_base_mapping(data_source_id,data_base_id,status) values(#{dataSourceId},#{dataBaseId},#{status})")
    int insert(ShardingSourceBaseMapping mapping);

    @Update("update sharding_source_base_mapping set status = -1 where data_source_id = #{dataSourceId}")
    int deleteByDataSourceId(Integer dataSourceId);

    List<ShardingSourceBaseMapping> selectByDataSourceId(Integer dataSourceId);
}
