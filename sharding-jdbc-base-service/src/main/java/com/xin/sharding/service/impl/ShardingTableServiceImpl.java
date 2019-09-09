package com.xin.sharding.service.impl;

import com.xin.sharding.mapper.ShardingTableMapper;
import com.xin.sharding.model.ShardingTable;
import com.xin.sharding.model.vo.ShardingGroupVO;
import com.xin.sharding.model.vo.ShardingLogicTableVO;
import com.xin.sharding.model.vo.ShardingTableVO;
import com.xin.sharding.service.ShardingDataSourceService;
import com.xin.sharding.service.ShardingGroupService;
import com.xin.sharding.service.ShardingLogicTableService;
import com.xin.sharding.service.ShardingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/15 12:36
 */
@Service
public class ShardingTableServiceImpl implements ShardingTableService {

    @Autowired
    ShardingTableMapper shardingTableMapper;

    @Autowired
    ShardingLogicTableService shardingLogicTableService;
    @Autowired
    ShardingDataSourceService shardingDataSourceService;

    @Autowired
    ShardingGroupService shardingGroupService;
    @Override
    public List<ShardingTableVO> getByProjectId(Integer id) {
        List<ShardingTableVO> list = new ArrayList<>();
        List<ShardingLogicTableVO> logicTableList = shardingLogicTableService.getAllByProjectId(id);
        logicTableList.forEach(lg -> {
            List<ShardingGroupVO> groupList = shardingGroupService.getAllByLogicTable(lg.getLogicTableId());
            groupList.forEach(g -> {
                List<ShardingTableVO> shardingTableVOS = getAllByGroup(g.getGroupId());
                shardingTableVOS.forEach(vo ->{
                    vo.setGroupId(g.getGroupId());
                    vo.setGroupName(g.getGroupName());
                    vo.setLogicTableId(lg.getLogicTableId());
                    vo.setLogicTableName(lg.getLogicTableName());
                    list.add(vo);
                });
            });
        });
        return list;
    }

    @Override
    public List<ShardingTableVO> getAllByGroup(Integer id) {
        List<ShardingTable> shardingTables = shardingTableMapper.selectByGroupId(id);
        List<ShardingTableVO> collect = shardingTables.stream().map(st -> {
            ShardingTableVO vo = new ShardingTableVO();
            vo.setTableId(st.getTableId());
            vo.setActualTableName(st.getActualTableName());
            vo.setDataSourceId(st.getDataSourceId());
            vo.setDataSourceName(st.getDataSourceName());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ShardingTable select(Integer id) {
        return null;
    }

    @Override
    public int update(ShardingTable shardingTable) {
        return 0;
    }

    @Override
    public int insert(ShardingTableVO vo){
        ShardingTable st = new ShardingTable();
        st.setActualTableName(vo.getActualTableName());
        st.setDataSourceId(vo.getDataSourceId());
        st.setGroupId(vo.getGroupId());
        return shardingTableMapper.insert(st);
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }


}
