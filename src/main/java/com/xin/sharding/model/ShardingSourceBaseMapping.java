package com.xin.sharding.model;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/18 16:27
 * 逻辑数据源与数据库绑定映射
 */
public class ShardingSourceBaseMapping {
    private Integer id;
    private Integer dataSourceId;
    private Integer dataBaseId;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(Integer dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
