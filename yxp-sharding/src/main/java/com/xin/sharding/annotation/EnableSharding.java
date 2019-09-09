package com.xin.sharding.annotation;


import com.xin.sharding.QuartzService;
import com.xin.sharding.RestTemplateConfig;
import com.xin.sharding.ShardingConfigurationFactory;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: songxiaoyue
 * @Date: 2018/9/21 16:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ShardingConfigurationFactory.class, QuartzService.class, RestTemplateConfig.class})
public @interface EnableSharding {
}