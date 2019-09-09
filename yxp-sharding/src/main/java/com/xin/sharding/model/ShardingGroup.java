package com.xin.sharding.model;

import java.util.List;

public class ShardingGroup {

    private Integer groupId;

    private Integer logicTableId;

    private String groupName;

    private Integer status;

    private List<ShardingTable> shardingTables;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getLogicTableId() {
        return logicTableId;
    }

    public void setLogicTableId(Integer logicTableId) {
        this.logicTableId = logicTableId;
    }

    public List<ShardingTable> getShardingTables() {
        return shardingTables;
    }

    public void setShardingTables(List<ShardingTable> shardingTables) {
        this.shardingTables = shardingTables;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}