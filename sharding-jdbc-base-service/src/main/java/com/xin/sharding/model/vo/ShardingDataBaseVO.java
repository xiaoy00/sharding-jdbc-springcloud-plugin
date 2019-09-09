package com.xin.sharding.model.vo;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 11:40
 */
public class ShardingDataBaseVO {

    private Integer dataBaseId;
    /**
     * 数据库名称
     */
    private String dataBaseName;
    /**
     * ip地址
     */
    private String address;

    /**
     * 主从 1:主库，0:从库
     */
    private Integer dataBaseType;

    public Integer getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(Integer dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public Integer getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(Integer dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
