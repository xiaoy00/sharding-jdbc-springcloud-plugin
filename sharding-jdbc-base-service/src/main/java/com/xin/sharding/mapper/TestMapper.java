package com.xin.sharding.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/19 18:52
 */
@Mapper
public interface TestMapper {


    @Insert("insert into order(oid) values(#{oid})")
    int insert(String oid);

}
