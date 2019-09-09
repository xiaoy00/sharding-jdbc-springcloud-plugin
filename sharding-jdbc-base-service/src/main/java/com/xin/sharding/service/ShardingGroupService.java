package com.xin.sharding.service;

import com.xin.sharding.model.ShardingGroup;
import com.xin.sharding.model.vo.ShardingGroupVO;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/12 15:47
 */
public interface ShardingGroupService {

    List<ShardingGroupVO> getByProjectId(Integer projectId);

    List<ShardingGroupVO> getAllByLogicTable(Integer id);

    ShardingGroup select(Integer id);

    int update(ShardingGroupVO shardingGroup);

    int insert(ShardingGroupVO shardingGroup);

    int delete(Integer id);

}
