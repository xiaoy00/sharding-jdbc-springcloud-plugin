package com.xin.sharding.service;

import com.xin.sharding.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/22 12:27
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    @Transactional
    public int insertb(String oid){
        testMapper.insert(oid+"bbbb");
        if("a".equals(oid)){
            Integer.parseInt(oid);
        }
        return 0;
    }
}
