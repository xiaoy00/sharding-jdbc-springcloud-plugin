package com.xin.sharding;

import com.alibaba.druid.pool.DruidDataSource;
import com.xin.sharding.algorithm.MyGroupComplexKeysShardingAlgorithm;
import com.xin.sharding.configuration.ShardingConfiguration;
import com.xin.sharding.id.SnowflakeIdWorker;
import com.xin.sharding.model.*;
import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.ComplexShardingStrategyConfiguration;
import io.shardingsphere.core.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/20 17:43
 */

public class ShardingConfigurationFactory implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationReadyEvent){
            System.out.println("yxp-sharding-0.0.4 started");
            getShardingConfiguration();
        }
    }
    @Bean
    public DataSource shardingDataSource(){
        return new ShardingDataSource();
    }
    /**
     * 创建所有分表规则
     * @param logicTables
     * @return
     */
    private List<TableRuleConfiguration> getTableRuleConfigurations(List<ShardingLogicTable> logicTables,Integer workId) {
        //按逻辑表名 分组
//        Map<String, List<ShardingGroup>> logicMap = groupList.stream().collect(Collectors.groupingBy(g -> g.getLogicName()));
        List<TableRuleConfiguration> list = new ArrayList<>();
        logicTables.forEach(logicTable ->
            list.add(createTableRule(logicTable,workId))
        );
        return list;
    }

    private TableRuleConfiguration createTableRule(ShardingLogicTable shardingLogicTable,Integer workId) {
        String logicTableName = shardingLogicTable.getLogicTableName();
        String shardingCol = shardingLogicTable.getShardingCol();
        String primaryKey = shardingLogicTable.getPrimaryKey();
        List<ShardingGroup> groups = shardingLogicTable.getGroups();
        String actualDataNodes = groups.stream().map(g -> g.getShardingTables().stream().map(t -> t.getDataSourceName() + "." + t.getActualTableName()).collect(Collectors.joining(","))).collect(Collectors.joining(","));
        ComplexShardingStrategyConfiguration complexShardingStrategyConfiguration = null;
        try {
            complexShardingStrategyConfiguration = new ComplexShardingStrategyConfiguration(shardingCol, new MyGroupComplexKeysShardingAlgorithm(groups));
        } catch (Exception e) {

        }
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        tableRuleConfiguration.setLogicTable(logicTableName);
        tableRuleConfiguration.setActualDataNodes(actualDataNodes);
        tableRuleConfiguration.setTableShardingStrategyConfig(complexShardingStrategyConfiguration);
        if(!StringUtils.isEmpty(primaryKey)){
            tableRuleConfiguration.setKeyGeneratorColumnName(primaryKey);
            tableRuleConfiguration.setKeyGenerator(SnowflakeIdWorker.getInstanceSnowflake(workId));
        }
        return tableRuleConfiguration;
    }


    /**
     * 创建所有主从配置
     * @param dataSourceInfos
     * @return
     */
    private List<MasterSlaveRuleConfiguration>  getMasterSlaveRuleConfigurations(List<ShardingDataSourceInfo> dataSourceInfos){
        return  dataSourceInfos.stream().map(ds -> createMasterSlaveRule(ds)).collect(Collectors.toList());
    }


    private MasterSlaveRuleConfiguration createMasterSlaveRule(ShardingDataSourceInfo shardingDataSourceInfo){
        String dataSourceName = shardingDataSourceInfo.getDataSourceName();

        Map<Integer, List<ShardingDataBase>> collect = shardingDataSourceInfo.getShardingDataBaseList()
                .stream().collect(Collectors.groupingBy(sd -> sd.getDataBaseType()));

        List<ShardingDataBase> masterList = collect.get(1);
        String dataBaseName = masterList.get(0).getDataBaseName();

        List<ShardingDataBase> slaveList = collect.get(0);
        List<String> slaveNameList;
        if(slaveList == null){
            slaveNameList = Arrays.asList(dataBaseName);
        }else {
            slaveNameList = slaveList.stream().map(s -> s.getDataBaseName()).collect(Collectors.toList());
        }

        return new MasterSlaveRuleConfiguration(dataSourceName,dataBaseName,slaveNameList);
    }


    /**
     * 创建所有数据源
     * @param dataSourceInfos
     * @return
     */
    private Map<String,DataSource> getDataSourceMap(List<ShardingDataSourceInfo> dataSourceInfos){
        Map<String,DataSource> dataSourceMap = new HashMap<>();
        dataSourceInfos.forEach(dataSourceInfo ->{
            List<ShardingDataBase> shardingDataBaseList = dataSourceInfo.getShardingDataBaseList();
            shardingDataBaseList.forEach(db ->{
                DataSource dataSource = createDataSource(db.getUrl(), db.getUserName(), db.getPassword(),db.getDriver());
                dataSourceMap.put(db.getDataBaseName(),dataSource);
            });
        });
        return dataSourceMap;
    }

    private DataSource createDataSource(String url,String userName,String password,String driver){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

/*    @Resource
    ShardingDataSource shardingDataSource;*/
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Environment env;

    public ShardingConfiguration getShardingConfiguration(){
        //shardingDataSource.close();
        String property = env.getProperty("spring.application.name");
        String showSql = env.getProperty("sharding.sql.show");
        ParameterizedTypeReference<ShardingConfig> responseType = new ParameterizedTypeReference<ShardingConfig>(){};
        ResponseEntity<ShardingConfig> exchange = restTemplate.exchange("http://sharding-jdbc-base-service/getShardingConfig?name={1}", HttpMethod.GET, null, responseType,property);
        ShardingConfig shardingConfig = exchange.getBody();
        ShardingConfiguration  shardingConfiguration = new ShardingConfiguration();
        if(!StringUtils.isEmpty(showSql)){
            shardingConfiguration.setSqlShow(true);
        }
        shardingConfiguration.setDataSourceMap(getDataSourceMap(shardingConfig.getDataSourceInfos()));
        shardingConfiguration.setMasterSlaveRuleConfigurations(getMasterSlaveRuleConfigurations(shardingConfig.getDataSourceInfos()));
        shardingConfiguration.setTableRuleConfigurations(getTableRuleConfigurations(shardingConfig.getLogicTables(),shardingConfig.getWorkId()));
        try {
            ShardingDataSource shardingDataSource = (ShardingDataSource)applicationContext.getBean("shardingDataSource");
            //获取更新前的数据源
            Map<String, DataSource> dataSourceMap = shardingDataSource.getDataSourceMap();

            ShardingDataSource shardingDataSource2 = (ShardingDataSource)shardingConfiguration.createShardingDataSource();
            shardingDataSource.setDataSourceMap(shardingDataSource2.getDataSourceMap());
            shardingDataSource.setShardingContext(shardingDataSource2.getShardingContext());
            shardingDataSource.setShardingProperties(shardingDataSource2.getShardingProperties());
            shardingDataSource.setDatabaseType(shardingDataSource2.getDatabaseType());
            shardingDataSource.setLogWriter(shardingDataSource2.getLogWriter());

            //关闭更新前的数据源
            if(dataSourceMap != null){
                Collection<DataSource> values = dataSourceMap.values();
                for (DataSource each :values) {
                    try {
                        each.getClass().getDeclaredMethod("close").invoke(each);
                    } catch (final ReflectiveOperationException ignored) {
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("出现重大错误， 数据源加载失败");
            e.printStackTrace();
        }

        return shardingConfiguration;
    }
}
