package com.xin.sharding.model;

public class ShardingDataBase {

    /**
     *
     */
    private Integer dataBaseId;
    /**
     * 逻辑数据源id
     */
    private Integer dataSourceId;
    /**
     * 主从 1:主库，0:从库
     */
    private Integer dataBaseType;

    /**
     * 数据库名称
     */
    private String dataBaseName;
    /**
     * ip地址
     */
    private String address;

    /**
     *
     */
    private String userName;

    private String password;

    private String url;

    private String driver;


    public Integer getDataBaseId() {
        return dataBaseId;
    }


    public void setDataBaseId(Integer dataBaseId) {
        this.dataBaseId = dataBaseId;
    }


    public Integer getDataSourceId() {
        return dataSourceId;
    }


    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }


    public Integer getDataBaseType() {
        return dataBaseType;
    }


    public void setDataBaseType(Integer dataBaseType) {
        this.dataBaseType = dataBaseType;
    }


    public String getDataBaseName() {
        return dataBaseName;
    }


    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}