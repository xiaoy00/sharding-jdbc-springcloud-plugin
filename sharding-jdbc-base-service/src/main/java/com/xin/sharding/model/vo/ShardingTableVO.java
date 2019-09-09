package com.xin.sharding.model.vo;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 16:15
 */
public class ShardingTableVO {

    private Integer tableId;
    private String actualTableName;
    private String groupName;
    private Integer groupId;
    private String  dataSourceName;
    private Integer dataSourceId;
    private Integer logicTableId;
    private String logicTableName;


    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getActualTableName() {
        return actualTableName;
    }

    public void setActualTableName(String actualTableName) {
        this.actualTableName = actualTableName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getLogicTableId() {
        return logicTableId;
    }

    public void setLogicTableId(Integer logicTableId) {
        this.logicTableId = logicTableId;
    }

    public String getLogicTableName() {
        return logicTableName;
    }

    public void setLogicTableName(String logicTableName) {
        this.logicTableName = logicTableName;
    }
}
