package com.xin.sharding.service.impl;

import com.car.resume.common.SysResult;
import com.xin.sharding.mapper.ShardingDataBaseMapper;
import com.xin.sharding.model.ShardingDataBase;
import com.xin.sharding.model.vo.ShardingDataBaseVO;
import com.xin.sharding.service.ShardingDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 11:29
 */
@Service
public class ShardingDataBaseServiceImpl implements ShardingDataBaseService{

    @Autowired
    ShardingDataBaseMapper shardingDataBaseMapper;


    @Override
    public List<ShardingDataBaseVO> getByDataSourceId(Integer dataSourceId) {
        List<ShardingDataBase> shardingDataBases = shardingDataBaseMapper.selectByDataSourceId(dataSourceId);
        List<ShardingDataBaseVO> collect = shardingDataBases.stream().map(sd -> {
            ShardingDataBaseVO vo = new ShardingDataBaseVO();
            vo.setDataBaseId(sd.getDataBaseId());
            vo.setDataBaseName(sd.getDataBaseName());
            vo.setDataBaseType(sd.getDataBaseType());
            vo.setAddress(sd.getAddress());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }
    @Override
    public List<ShardingDataBaseVO> getAll(){
        List<ShardingDataBase> list = shardingDataBaseMapper.selectAll();
        List<ShardingDataBaseVO> collect = list.stream().map(sdb -> {
            ShardingDataBaseVO vo = new ShardingDataBaseVO();
            vo.setDataBaseId(sdb.getDataBaseId());
            vo.setDataBaseName(sdb.getDataBaseName());
            vo.setAddress(sdb.getAddress());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ShardingDataBase select(Integer id) {
        return null;
    }

    @Override
    public int update(ShardingDataBase shardingDataBase) {
        return 0;
    }

    @Override
    public SysResult insert(ShardingDataBaseVO vo) {
        //查询配置文件，看是否存在该 数据库配置
        List<String> availableList = getAvailableList();
        boolean contains = availableList.contains(vo.getDataBaseName());
        if(!contains){
            return new SysResult(-1,"请检查配置文件");
        }
        ShardingDataBase db = new ShardingDataBase();
        db.setDataBaseName(vo.getDataBaseName());
        db.setAddress(vo.getAddress());
        int insert = shardingDataBaseMapper.insert(db);
        if(insert == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }





    @ConfigurationProperties(prefix="spring.datasource")
    @Bean("dataBaseMap")
    public Map<String,Object> dataBaseMap(){
        Map<String,Object> map = new HashMap<>();
        return map;
    }

    @Qualifier("dataBaseMap")
    @Autowired
    Map<String,Object> dataBaseMap;

    @Override
    public List<String> getAvailableList() {
        Map<String,Object> map = (Map<String, Object>) dataBaseMap.get("dataBaseMap");
        List<String> collect = map.entrySet().stream().filter(entry -> {
            Object value = entry.getValue();
            if (value instanceof Map) {
                String name = (String) ((Map) value).get("name");
                String url = (String) ((Map) value).get("url");
                String username = (String) ((Map) value).get("username");
                String password = (String) ((Map) value).get("password");
                return !StringUtils.isEmpty(url);
            }
            return false;
        }).map(entry ->entry.getKey()).collect(Collectors.toList());
        return  collect;
    }

}
