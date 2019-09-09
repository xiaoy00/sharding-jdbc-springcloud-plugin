package com.xin.sharding.model;

public class ShardingTable {

    private Integer tableId;

    private Integer dataSourceId;

    private Integer groupId;

    private String actualTableName;

    private String dataSourceName;


    public Integer getTableId() {
        return tableId;
    }


    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getActualTableName() {
        return actualTableName;
    }

    public void setActualTableName(String actualTableName) {
        this.actualTableName = actualTableName;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}