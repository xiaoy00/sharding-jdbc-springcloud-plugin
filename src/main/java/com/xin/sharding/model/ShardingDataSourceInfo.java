package com.xin.sharding.model;

import java.util.List;

public class ShardingDataSourceInfo {

    /**
     * 逻辑数据源id
     */
    private Integer dataSourceId;

    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 逻辑数据源名称
     */
    private String dataSourceName;

    /**
     * 实际数据源名称
     */
    private List<ShardingDataBase> shardingDataBaseList;

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public List<ShardingDataBase> getShardingDataBaseList() {
        return shardingDataBaseList;
    }

    public void setShardingDataBaseList(List<ShardingDataBase> shardingDataBaseList) {
        this.shardingDataBaseList = shardingDataBaseList;
    }

}