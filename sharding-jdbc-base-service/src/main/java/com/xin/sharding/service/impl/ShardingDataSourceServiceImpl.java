package com.xin.sharding.service.impl;

import com.xin.sharding.mapper.ShardingDataSourceMapper;
import com.xin.sharding.model.ShardingDataSourceInfo;
import com.xin.sharding.model.ShardingSourceBaseMapping;
import com.xin.sharding.model.vo.ShardingDataBaseVO;
import com.xin.sharding.model.vo.ShardingDataSourceVO;
import com.xin.sharding.service.ShardingDataBaseService;
import com.xin.sharding.service.ShardingDataSourceService;
import com.xin.sharding.service.ShardingSourceBaseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/12 15:23
 */
@Service
public class ShardingDataSourceServiceImpl implements ShardingDataSourceService {

    @Autowired
    ShardingDataSourceMapper shardingDataSourceMapper;
    @Autowired
    ShardingDataBaseService shardingDataBaseService;
    @Autowired
    ShardingSourceBaseMappingService shardingSourceBaseMappingService;

    @Override
    public List<ShardingDataSourceVO> getAllByProjectId(Integer projectId) {
        List<ShardingDataSourceInfo> shardingDataSourceInfos = shardingDataSourceMapper.selectByProjectId(projectId);


        List<ShardingDataSourceVO> collect = shardingDataSourceInfos.stream().map(sd -> {
            ShardingDataSourceVO vo = new ShardingDataSourceVO();
            vo.setDataSourceId(sd.getDataSourceId());
            vo.setDataSourceName(sd.getDataSourceName());
            List<ShardingDataBaseVO> dataBases = shardingDataBaseService.getByDataSourceId(sd.getDataSourceId());
            ShardingDataBaseVO master = dataBases.stream().filter(d -> d.getDataBaseType() == 1).collect(Collectors.toList()).get(0);
            List<ShardingDataBaseVO> slaves = dataBases.stream().filter(d -> d.getDataBaseType() == 0).collect(Collectors.toList());
            vo.setMaster(master);
            vo.setSlaveList(slaves);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ShardingDataSourceVO getById(Integer id) {
        ShardingDataSourceInfo shardingDataSourceInfo = shardingDataSourceMapper.selectByPrimaryKey(id);
        ShardingDataSourceVO vo = new ShardingDataSourceVO();
        vo.setDataSourceId(shardingDataSourceInfo.getDataSourceId());
        vo.setDataSourceName(shardingDataSourceInfo.getDataSourceName());
        List<ShardingDataBaseVO> dataBases = shardingDataBaseService.getByDataSourceId(shardingDataSourceInfo.getDataSourceId());
        ShardingDataBaseVO master = dataBases.stream().filter(d -> d.getDataBaseType() == 1).collect(Collectors.toList()).get(0);
        List<ShardingDataBaseVO> slaves = dataBases.stream().filter(d -> d.getDataBaseType() == 0).collect(Collectors.toList());
        vo.setMaster(master);
        vo.setSlaveList(slaves);
        return vo;
    }

    @Override
    public ShardingDataSourceInfo select(Integer id) {
        return shardingDataSourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(ShardingDataSourceVO vo) {
        ShardingDataSourceInfo shardingDataSourceInfo = new ShardingDataSourceInfo();
        shardingDataSourceInfo.setDataSourceId(vo.getDataSourceId());
        shardingDataSourceInfo.setDataSourceName(vo.getDataSourceName());
        shardingDataSourceInfo.setProjectId(vo.getProjectId());

        int update = shardingDataSourceMapper.updateByPrimaryKey(shardingDataSourceInfo);
        if(update > 0){
            updateMapping(vo.getDataSourceId(),vo.getMaster(),vo.getSlaveList());
        }
        return update;
    }
    @Override
    public int insert(ShardingDataSourceVO vo) {
        ShardingDataSourceInfo shardingDataSourceInfo = new ShardingDataSourceInfo();
        shardingDataSourceInfo.setDataSourceName(vo.getDataSourceName());
        shardingDataSourceInfo.setProjectId(vo.getProjectId());

        int insert = shardingDataSourceMapper.insert(shardingDataSourceInfo);
        if(insert > 0){
            updateMapping(shardingDataSourceInfo.getDataSourceId(),vo.getMaster(),vo.getSlaveList());
        }
        return insert;
    }


    private int updateMapping(Integer dataSourceId,ShardingDataBaseVO master,List<ShardingDataBaseVO> slaveList){
        List<ShardingSourceBaseMapping> mappingList = slaveList.stream().map(slave -> {
            ShardingSourceBaseMapping slaveMapping = new ShardingSourceBaseMapping();
            slaveMapping.setDataSourceId(dataSourceId);
            slaveMapping.setDataBaseId(slave.getDataBaseId());
            slaveMapping.setStatus(0);
            return slaveMapping;
        }).collect(Collectors.toList());

        ShardingSourceBaseMapping masterMapping = new ShardingSourceBaseMapping();
        masterMapping.setDataSourceId(dataSourceId);
        masterMapping.setDataBaseId(master.getDataBaseId());
        masterMapping.setStatus(1);
        mappingList.add(masterMapping);
        return shardingSourceBaseMappingService.batch(mappingList, dataSourceId);
    }



    @Override
    public int delete(Integer id) {
        return shardingDataSourceMapper.deleteByPrimaryKey(id);
    }


}
