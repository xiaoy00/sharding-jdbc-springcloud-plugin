package com.xin.sharding.service;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.ShardingDataBase;
import com.xin.sharding.model.vo.ShardingDataBaseVO;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/12 15:46
 */
public interface ShardingDataBaseService {

    List<ShardingDataBaseVO> getByDataSourceId(Integer dataSourceId);

    List<ShardingDataBaseVO> getAll();

    ShardingDataBase select(Integer id);

    int update(ShardingDataBase shardingDataBase);

    SysResult insert(ShardingDataBaseVO shardingDataBase);

    int delete(Integer id);

    List<String> getAvailableList();
}
