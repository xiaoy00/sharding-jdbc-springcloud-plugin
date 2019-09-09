package com.xin.sharding.service.impl;

import com.xin.sharding.mapper.ShardingGroupMapper;
import com.xin.sharding.model.ShardingGroup;
import com.xin.sharding.model.vo.ShardingGroupVO;
import com.xin.sharding.model.vo.ShardingLogicTableVO;
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
 * @Date: 2018/10/15 20:17
 */
@Service
public class ShardingGroupServiceImpl implements ShardingGroupService {

    @Autowired
    ShardingGroupMapper shardingGroupMapper;
    @Autowired
    ShardingTableService shardingTableService;

    @Autowired
    ShardingLogicTableService shardingLogicTableService;

    @Override
    public List<ShardingGroupVO> getByProjectId(Integer projectId) {
        List<ShardingLogicTableVO> logicTableList = shardingLogicTableService.getAllByProjectId(projectId);
        List<ShardingGroupVO> list = new ArrayList<>();
        logicTableList.forEach(lt -> {
            List<ShardingGroupVO> groupVOList = getAllByLogicTable(lt.getLogicTableId());
            groupVOList.forEach(g ->{
                g.setLogicTableName(lt.getLogicTableName());
            });
            list.addAll(groupVOList);
        });
        return list;
    }

    @Override
    public List<ShardingGroupVO> getAllByLogicTable(Integer id) {
        List<ShardingGroup> shardingGroups = shardingGroupMapper.selectByLogicTableId(id);
        List<ShardingGroupVO> collect = shardingGroups.stream().map(sg -> {
            ShardingGroupVO vo = new ShardingGroupVO();
            vo.setGroupName(sg.getGroupName());
            vo.setGroupId(sg.getGroupId());
            vo.setStatus(sg.getStatus());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ShardingGroup select(Integer id) {
        return null;
    }

    @Override
    public int update(ShardingGroupVO shardingGroupVO) {
        return 0;
    }

    /**
     * 扩容新的组，把老的组设置为普通组
     * @param shardingGroupVO
     * @return
     */
    @Override
    public int insert(ShardingGroupVO shardingGroupVO) {
        ShardingGroup group = new ShardingGroup();
        group.setGroupName(shardingGroupVO.getGroupName());
        group.setLogicTableId(shardingGroupVO.getLogicTableId());
        group.setStatus(1);
        shardingGroupMapper.updateOldGroup(shardingGroupVO.getLogicTableId());

        return  shardingGroupMapper.insert(group);
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}
