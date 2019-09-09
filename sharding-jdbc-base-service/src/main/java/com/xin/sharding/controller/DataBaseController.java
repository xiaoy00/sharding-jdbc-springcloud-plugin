package com.xin.sharding.controller;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.vo.ShardingDataBaseVO;
import com.xin.sharding.service.ShardingDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 18:05
 */
@RestController
@RequestMapping("dataBase")
public class DataBaseController {

    @Autowired
    ShardingDataBaseService shardingDataBaseService;


    @RequestMapping("list")
    public Map getAllDateBase(){
        List<ShardingDataBaseVO> all = shardingDataBaseService.getAll();
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","");
        return map;
    }


    @RequestMapping("add")
    public SysResult add(@RequestBody ShardingDataBaseVO vo){
        return shardingDataBaseService.insert(vo);
    }

}
