package com.xin.sharding.controller;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.vo.ShardingGroupVO;
import com.xin.sharding.service.ShardingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 13:04
 */
@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    ShardingGroupService shardingGroupService;



    @RequestMapping("byProject")
    public Map<String, Object> byProject(Integer projectId){
        List<ShardingGroupVO> all = shardingGroupService.getByProjectId(projectId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","");
        return map;
    }

    @RequestMapping("byLogicTable")
    public Map<String, Object> byLogicTable(Integer logicTableId){
        List<ShardingGroupVO> all = shardingGroupService.getAllByLogicTable(logicTableId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","");
        return map;
    }

    @RequestMapping("add")
    public SysResult add(@RequestBody ShardingGroupVO vo){
        int insert = shardingGroupService.insert(vo);
        if(insert == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }


}
