package com.xin.sharding.model;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/9 14:59
 */
public class ShardingConfig {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 数据源配置集合
     */
    private List<ShardingDataSourceInfo> dataSourceInfos;
    /**
     * 分表配置集合
     */
    private List<ShardingLogicTable> logicTables;

    private Integer workId;

    public ShardingConfig() {
    }

    public ShardingConfig(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ShardingDataSourceInfo> getDataSourceInfos() {
        return dataSourceInfos;
    }

    public void setDataSourceInfos(List<ShardingDataSourceInfo> dataSourceInfos) {
        this.dataSourceInfos = dataSourceInfos;
    }

    public List<ShardingLogicTable> getLogicTables() {
        return logicTables;
    }

    public void setLogicTables(List<ShardingLogicTable> logicTables) {
        this.logicTables = logicTables;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
