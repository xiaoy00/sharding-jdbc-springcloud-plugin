package com.xin.sharding.service.impl;

import com.car.resume.util.RedisUtil;
import com.xin.sharding.mapper.*;
import com.xin.sharding.model.*;
import com.xin.sharding.service.ShardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/19 16:02
 */
@Service
public class ShardingServiceImpl implements ShardingService {

    @Autowired
    ShardingDataBaseMapper shardingDataBaseMapper;
    @Autowired
    ShardingDataSourceMapper shardingDataSourceMapper;
    @Autowired
    ShardingProjectMapper shardingProjectMapper;
    @Autowired
    ShardingGroupMapper shardingGroupMapper;
    @Autowired
    ShardingTableMapper shardingTableMapper;
    @Autowired
    ShardingLogicTableMapper shardingLogicTableMapper;
    @Autowired
    private Environment env;
    @Autowired
    RedisUtil redisUtils;


    Random ra =new Random();
    //8位workId
    private static final long maxWorkerId = -1L ^ (-1L << 8);

    public Integer getWorkId(Integer projectId){
        int random = ra.nextInt((int)maxWorkerId -1) + 1;
        String workId = "workId:" + projectId + ":" + random;
        long expire = redisUtils.getExpire(workId);

        if(expire < 0){
            redisUtils.set(workId,"",3600L);
            return random;
        }
        return getWorkId(projectId);

    }

    @Override
    public ShardingConfig getShardingConfig(String projectName){
        ShardingConfig shardingConfig = new ShardingConfig(projectName);
        ShardingProject shardingProject = getShardingProject(projectName);
        if(shardingProject == null){
            return null;
        }
        Integer projectId = shardingProject.getProjectId();
        shardingConfig.setDataSourceInfos(getShardingDataSources(projectId));
        shardingConfig.setLogicTables(getShardingLogicTables(projectId));
        shardingConfig.setWorkId(getWorkId(projectId));
        return shardingConfig;
    }

    private ShardingProject getShardingProject(String projectName) {
        return shardingProjectMapper.selectByProjectName(projectName);
    }

    /**
     * 根据项目id获取分组配置
     * @param projectId
     * @return
     */
    private List<ShardingLogicTable> getShardingLogicTables(Integer projectId) {
        List<ShardingLogicTable> shardingLogicTables = shardingLogicTableMapper.selectByProjectId(projectId);
        shardingLogicTables.forEach(logic ->{
            List<ShardingGroup> shardingGroups = shardingGroupMapper.selectByLogicTableId(logic.getLogicTableId());
            shardingGroups.forEach(group ->{
                group.setShardingTables(shardingTableMapper.selectByGroupId(group.getGroupId()));
            });
            logic.setGroups(shardingGroups);
        });
        return shardingLogicTables;
    }

    /**
     * 根据项目id获取数据源配置
     * @param projectId
     * @return
     */
    private List<ShardingDataSourceInfo> getShardingDataSources(Integer projectId) {
        List<ShardingDataSourceInfo> shardingDataSourceInfos = shardingDataSourceMapper.selectByProjectId(projectId);
        shardingDataSourceInfos.forEach(ds ->{
            ds.setShardingDataBaseList(getShardingDataBases(ds.getDataSourceId()));
        });
        return shardingDataSourceInfos;
    }

    private List<ShardingDataBase> getShardingDataBases(Integer dataSourceId) {
        List<ShardingDataBase> shardingDataBases = shardingDataBaseMapper.selectByDataSourceId(dataSourceId);
        shardingDataBases.forEach(db -> {
            String key = "spring.datasource."+ db.getDataBaseName();
            //TODO: 2018/10/9   是否需要。
            //db.setDataBaseName(getConfigString(key+".name"));
            db.setUrl(getConfigString(key+".url"));
            db.setUserName(getConfigString(key+".username"));
            db.setPassword(getConfigString(key+".password"));
        });
        return shardingDataBases;
    }

    /**
     * 根据实际数据库名 取 url,username,password
     * 配置按照以下规律或者写进数据库
     * ${spring.datasource.逻辑库名.url,username,password}
     * **/
    private String getConfigString(String configName){
       return env.getProperty(configName);
    }
}
