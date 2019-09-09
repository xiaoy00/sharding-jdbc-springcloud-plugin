package com.xin.sharding.algorithm;

import com.xin.sharding.model.ShardingGroup;
import io.shardingsphere.core.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.sql.dml.insert.InsertStatement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分表算法：
 * 前缀最大的组是insert专用，保证数据只写入到扩容后的分组。
 * 其他方法，都是所有分组轮询
 * 每个分组的table数量不固定
 * 每个分组的散列方法一致
 * @Author: songxiaoyue
 * @Date: 2018/8/3 18:51
 */
public class MyGroupComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm{

    private List<ShardingGroup> groupInfos;

    private ShardingGroup activeGroup;

    public MyGroupComplexKeysShardingAlgorithm(List<ShardingGroup> groupInfos) throws Exception {
        if(groupInfos == null || groupInfos.size() == 0){
            throw new Exception("group info can not be empty");
        }

        //// TODO: 2018/10/9   status 改枚举
        List<ShardingGroup> collect = groupInfos.stream().filter(g -> g.getStatus() == 1).collect(Collectors.toList());
        if(collect.size() == 0){
            throw new Exception("no active group");
        }
        groupInfos.sort(Comparator.comparing(ShardingGroup::getStatus));
        this.activeGroup = collect.get(collect.size()-1);
        this.groupInfos = groupInfos;
    }

    @Override
    public Collection<String> doSharding(Collection<String> tables, Collection<ShardingValue> shardingValues,SQLStatement sqlStatement) {
        List<String> result = new ArrayList<>();
        String value = ((ListShardingValue) shardingValues.toArray()[0]).getValues().toArray()[0].toString();
        List<ShardingGroup> group = new ArrayList<>();

        if(sqlStatement != null && sqlStatement instanceof InsertStatement){
            group.add(activeGroup);
        }else {
            group = groupInfos;
        }
        group.forEach((g)->{
            int i = value.hashCode();
            String tableName = g.getShardingTables().get(Math.abs(i % g.getShardingTables().size())).getActualTableName();
            if(tables.contains(tableName)){
                result.add(tableName);
            }
        });
        return result;
    }


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        //不需要该方法。
        System.err.println("什么情况呢。");
        return null;
    }

}
