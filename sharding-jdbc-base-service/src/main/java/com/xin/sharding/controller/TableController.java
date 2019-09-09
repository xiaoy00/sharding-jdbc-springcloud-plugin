package com.xin.sharding.controller;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.ShardingTable;
import com.xin.sharding.model.vo.ShardingTableVO;
import com.xin.sharding.service.ShardingTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/11 12:31
 */
@RestController
@RequestMapping("table")
public class TableController {

    @Autowired
    ShardingTableService shardingTableService;

    @RequestMapping("byProject")
    public Map<String, Object> tableListByProject(Integer projectId){
        List<ShardingTableVO> all = shardingTableService.getByProjectId(projectId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","ok");
        return map;
    }

    @RequestMapping("byGroup")
    public Map<String, Object> tableListByGroup(Integer groupId){
        shardingTableService.getAllByGroup(groupId);
        List<ShardingTableVO> all = shardingTableService.getAllByGroup(groupId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","ok");
        return map;
    }

    @RequestMapping("get")
    public SysResult get(Integer id){
        ShardingTable select = shardingTableService.select(id);
        return new SysResult<>(1,"ok",select);
    }

    @RequestMapping("update")
    public SysResult update(ShardingTable shardingTable){
        int update = shardingTableService.update(shardingTable);
        if(update == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }
    @RequestMapping("add")
    public SysResult insert(@RequestBody ShardingTableVO vo){

        int insert = shardingTableService.insert(vo);
        if(insert == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

    @RequestMapping("delete")
    public SysResult delete(Integer tableId){
        int delete = shardingTableService.delete(tableId);
        if(delete == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

}
