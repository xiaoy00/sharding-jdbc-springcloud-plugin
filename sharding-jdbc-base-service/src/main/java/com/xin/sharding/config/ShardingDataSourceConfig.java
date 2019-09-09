package com.xin.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.xin.sharding.configuration.ShardingConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/18 10:48
 */
@Configuration
public class ShardingDataSourceConfig {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;
    @Bean
    public DataSource getShardingDataSource() throws SQLException {
        ShardingConfiguration shardingConfiguration = new ShardingConfiguration();
        shardingConfiguration.setDataSourceMap(createDataSourceMap());
        shardingConfiguration.setMasterSlaveRuleConfigurations(new ArrayList<>());
        shardingConfiguration.setTableRuleConfigurations(new ArrayList<>());
        DataSource shardingDataSource = shardingConfiguration.createShardingDataSource();
        return shardingDataSource;
    }

    public Map<String, DataSource> createDataSourceMap() {
        DataSource dataSource = getDataSource();
        Map<String, DataSource> map = new HashMap<>();
        map.put("ds",dataSource);
        return map;
    }

    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

}
