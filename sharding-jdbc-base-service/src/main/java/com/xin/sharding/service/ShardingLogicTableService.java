package com.xin.sharding.service;

import com.xin.sharding.model.ShardingLogicTable;
import com.xin.sharding.model.vo.ShardingLogicTableVO;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/12 15:47
 */
public interface ShardingLogicTableService {

    List<ShardingLogicTableVO> getAllByProjectId(Integer id);

    ShardingLogicTableVO select(Integer id);

    int update(ShardingLogicTable shardingLogicTable);

    int insert(ShardingLogicTableVO shardingLogicTable);

    int delete(Integer id);

    List<ShardingLogicTable> getByProjectId(Integer id);
}
