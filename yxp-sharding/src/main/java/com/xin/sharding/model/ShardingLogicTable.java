package com.xin.sharding.model;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/10 10:48
 */
public class ShardingLogicTable {

    private Integer logicTableId;
    private Integer projectId;
    private String logicTableName;
    private String primaryKey;
    private String shardingCol;
    private List<ShardingGroup> groups;

    public Integer getLogicTableId() {
        return logicTableId;
    }

    public void setLogicTableId(Integer logicTableId) {
        this.logicTableId = logicTableId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getLogicTableName() {
        return logicTableName;
    }

    public void setLogicTableName(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<ShardingGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ShardingGroup> groups) {
        this.groups = groups;
    }

    public String getShardingCol() {
        return shardingCol;
    }

    public void setShardingCol(String shardingCol) {
        this.shardingCol = shardingCol;
    }
}
