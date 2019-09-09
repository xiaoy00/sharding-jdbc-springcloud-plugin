package com.xin.sharding.service.impl;

import com.xin.sharding.mapper.ShardingProjectMapper;
import com.xin.sharding.model.ShardingProject;
import com.xin.sharding.service.ShardingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/11 12:33
 */
@Service
public class ShardingProjectServiceImpl implements ShardingProjectService{

    @Autowired
    ShardingProjectMapper shardingProjectMapper;

    @Override
    public List<ShardingProject> getAll() {
        List<ShardingProject> shardingProjects = shardingProjectMapper.selectAll();
        return shardingProjects;
    }

    @Override
    public ShardingProject select(Integer id) {
        return shardingProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(ShardingProject shardingProject) {
        return shardingProjectMapper.updateByPrimaryKey(shardingProject);
    }

    @Override
    public int insert(ShardingProject shardingProject) {
        return shardingProjectMapper.insert(shardingProject);
    }

    @Override
    public int delete(Integer id) {
        return shardingProjectMapper.deleteByPrimaryKey(id);
    }
}
