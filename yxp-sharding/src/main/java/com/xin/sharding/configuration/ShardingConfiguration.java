package com.xin.sharding.configuration;

import com.xin.sharding.id.SnowflakeIdWorker;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: songxiaoyue
 * @Date: 2018/8/2 18:28
 */

public class ShardingConfiguration {

    private Boolean sqlShow;
    private List<TableRuleConfiguration> tableRuleConfigurations;
    private List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigurations;
    private Map<String,DataSource> dataSourceMap;

    public DataSource createShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        //统一配置 主键
        tableRuleConfigurations.forEach(l ->{
            if(l.getKeyGeneratorColumnName() == null){
                l.setKeyGeneratorColumnName("id");
                l.setKeyGenerator(SnowflakeIdWorker.getInstanceSnowflake(1));
            }
        });
        shardingRuleConfig.setTableRuleConfigs(tableRuleConfigurations);
        shardingRuleConfig.setMasterSlaveRuleConfigs(masterSlaveRuleConfigurations);
        Properties properties = new Properties();
        properties.setProperty("sql.show", (sqlShow == null ? false : sqlShow) + "");
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<>(), properties);
        return dataSource;
    }

    public void setSqlShow(Boolean sqlShow) {
        this.sqlShow = sqlShow;
    }

    public void setTableRuleConfigurations(List<TableRuleConfiguration> tableRuleConfigurations) {
        this.tableRuleConfigurations = tableRuleConfigurations;
    }

    public void setMasterSlaveRuleConfigurations(List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigurations) {
        this.masterSlaveRuleConfigurations = masterSlaveRuleConfigurations;
    }

    public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    public Boolean getSqlShow() {
        return sqlShow;
    }

    public List<TableRuleConfiguration> getTableRuleConfigurations() {
        return tableRuleConfigurations;
    }

    public List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        return masterSlaveRuleConfigurations;
    }

    public Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }
}
