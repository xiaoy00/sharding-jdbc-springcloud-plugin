package com.xin.sharding.model.vo;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 16:22
 */
public class ShardingGroupVO {

    private Integer groupId;
    private String groupName;
    private Integer status;
    private String logicTableName;
    private Integer logicTableId;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLogicTableName() {
        return logicTableName;
    }

    public void setLogicTableName(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    public Integer getLogicTableId() {
        return logicTableId;
    }

    public void setLogicTableId(Integer logicTableId) {
        this.logicTableId = logicTableId;
    }
}
