package com.xin.sharding.model.vo;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 11:37
 */
public class ShardingDataSourceVO {

    /**
     * 逻辑数据源id
     */
    private Integer dataSourceId;
    private Integer projectId;
    /**
     * 逻辑数据源名称
     */
    private String dataSourceName;

    private ShardingDataBaseVO master;

    private List<ShardingDataBaseVO> slaveList;

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

    public ShardingDataBaseVO getMaster() {
        return master;
    }

    public void setMaster(ShardingDataBaseVO master) {
        this.master = master;
    }

    public List<ShardingDataBaseVO> getSlaveList() {
        return slaveList;
    }

    public void setSlaveList(List<ShardingDataBaseVO> slaveList) {
        this.slaveList = slaveList;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
