package com.xin.sharding.model.vo;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/15 20:23
 */
public class ShardingLogicTableVO {

    private Integer logicTableId;
    private Integer projectId;
    private String logicTableName;
    private String primaryKey;
    private String shardingCol;
    private List<ShardingGroupVO> groupList;

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

    public String getShardingCol() {
        return shardingCol;
    }

    public void setShardingCol(String shardingCol) {
        this.shardingCol = shardingCol;
    }

    public List<ShardingGroupVO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<ShardingGroupVO> groupList) {
        this.groupList = groupList;
    }
}
