package com.xin.sharding.service.impl;

import com.xin.sharding.mapper.ShardingLogicTableMapper;
import com.xin.sharding.model.ShardingLogicTable;
import com.xin.sharding.model.vo.ShardingGroupVO;
import com.xin.sharding.model.vo.ShardingLogicTableVO;
import com.xin.sharding.service.ShardingGroupService;
import com.xin.sharding.service.ShardingLogicTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/15 20:13
 */
@Service
public class ShardingLogicTableServiceImpl implements ShardingLogicTableService{

    @Autowired
    ShardingLogicTableMapper shardingLogicTableMapper;
    @Autowired
    ShardingGroupService shardingGroupService;
    @Override
    public List<ShardingLogicTableVO> getAllByProjectId(Integer id) {
        List<ShardingLogicTable> shardingLogicTables = shardingLogicTableMapper.selectByProjectId(id);
        List<ShardingLogicTableVO> collect = shardingLogicTables.stream().map(slt -> {
            ShardingLogicTableVO vo = new ShardingLogicTableVO();
            vo.setLogicTableId(slt.getLogicTableId());
            vo.setLogicTableName(slt.getLogicTableName());
            vo.setPrimaryKey(slt.getPrimaryKey());
            vo.setProjectId(slt.getProjectId());
            vo.setShardingCol(slt.getShardingCol());
            List<ShardingGroupVO> shardingGroups = shardingGroupService.getAllByLogicTable(slt.getLogicTableId());
            vo.setGroupList(shardingGroups == null ? new ArrayList<>() : shardingGroups);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ShardingLogicTableVO select(Integer id) {
        ShardingLogicTable slt = shardingLogicTableMapper.selectByPrimaryKey(id);
        ShardingLogicTableVO vo = new ShardingLogicTableVO();
        vo.setLogicTableId(slt.getLogicTableId());
        vo.setLogicTableName(slt.getLogicTableName());
        vo.setPrimaryKey(slt.getPrimaryKey());
        vo.setProjectId(slt.getProjectId());
        vo.setShardingCol(slt.getShardingCol());
        List<ShardingGroupVO> shardingGroups = shardingGroupService.getAllByLogicTable(slt.getLogicTableId());
        vo.setGroupList(shardingGroups == null ? new ArrayList<>() : shardingGroups);
        return vo;
    }

    @Override
    public int update(ShardingLogicTable shardingLogicTable) {
        return 0;
    }

    @Override
    public int insert(ShardingLogicTableVO vo) {
        ShardingLogicTable st = new ShardingLogicTable();
        st.setLogicTableName(vo.getLogicTableName());
        st.setPrimaryKey(vo.getPrimaryKey());
        st.setShardingCol(vo.getShardingCol());
        st.setProjectId(vo.getProjectId());
        return shardingLogicTableMapper.insert(st);
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public List<ShardingLogicTable> getByProjectId(Integer id) {
        return null;
    }
}
