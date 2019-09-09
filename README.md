# sharding-jdbc-plugin

sharding-jdbc 通用组件，基于sharding-jdbc 3.0.0.M4版本

环境：springcloud。

实现原理：项目启动时，通过rpc请求获取分表配置，动态创建sharding数据源（需要修改sharding-jdbc源码，支持分组配置）
