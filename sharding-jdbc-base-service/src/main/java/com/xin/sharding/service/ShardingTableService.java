package com.xin.sharding.service;

import com.xin.sharding.model.ShardingTable;
import com.xin.sharding.model.vo.ShardingTableVO;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/12 15:51
 */

public interface ShardingTableService {

    List<ShardingTableVO> getAllByGroup(Integer id);

    ShardingTable select(Integer id);

    int update(ShardingTable shardingTable);

    int insert(ShardingTableVO shardingTableVO);

    int delete(Integer id);

    List<ShardingTableVO> getByProjectId(Integer id);
}
