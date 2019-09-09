package com.xin.sharding.mapper;

import com.xin.sharding.model.ShardingProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShardingProjectMapper {

    int deleteByPrimaryKey(Integer projectId);

    int insert(ShardingProject record);

    int insertSelective(ShardingProject record);

    ShardingProject selectByPrimaryKey(Integer projectId);

    List<ShardingProject> selectAll();

    /**
     *  根据项目名获取项目信息
     */
    ShardingProject selectByProjectName(String projectName);

    int updateByPrimaryKeySelective(ShardingProject record);

    int updateByPrimaryKey(ShardingProject record);
}