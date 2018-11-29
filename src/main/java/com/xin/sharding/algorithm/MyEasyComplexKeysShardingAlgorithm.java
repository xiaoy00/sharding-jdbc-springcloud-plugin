

import io.shardingsphere.core.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 分表算法：
 * 前缀最大的组是insert专用，保证数据只写入到扩容后的分组。
 * 其他方法，都是所有分组轮询
 * 每个分组的table数量不固定
 * 每个分组的散列方法一致
 * @Author: songxiaoyue
 * @Date: 2018/8/3 18:51
 */
public class MyEasyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm{


    @Override
    public Collection<String> doSharding(Collection<String> tables, Collection<ShardingValue> shardingValues,SQLStatement sqlStatement) {
        List<String> result = new ArrayList<>();
        String value = ((ListShardingValue) shardingValues.toArray()[0]).getValues().toArray()[0].toString();


        int i = value.hashCode() % tables.size();
        Object o = tables.toArray()[i];
        result.add((String) o);
        return result;
    }


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        //不需要该方法。
        System.err.println("什么情况呢。");
        return null;
    }

}
