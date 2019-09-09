package com.xin.sharding.controller;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.ShardingLogicTable;
import com.xin.sharding.model.vo.ShardingLogicTableVO;
import com.xin.sharding.service.ShardingLogicTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/16 16:14
 */
@RestController
@RequestMapping("logicTable")
public class LogicTableController {
    @Autowired
    ShardingLogicTableService shardingLogicTableService;

    @RequestMapping("list")
    public Map<String, Object> tableList(Integer projectId){
        List<ShardingLogicTableVO> all = shardingLogicTableService.getAllByProjectId(projectId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","ok");
        return map;
    }

    @RequestMapping("get")
    public SysResult get(Integer logicTableId){
        ShardingLogicTableVO select = shardingLogicTableService.select(logicTableId);
        return new SysResult<>(1,"ok",select);
    }

    @RequestMapping("update")
    public SysResult update(@RequestBody ShardingLogicTable shardingProject){
        int update = shardingLogicTableService.update(shardingProject);
        if(update == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }
    @RequestMapping("add")
    public SysResult insert(@RequestBody ShardingLogicTableVO vo){

        int insert = shardingLogicTableService.insert(vo);
        if(insert == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

    @RequestMapping("delete")
    public SysResult delete(Integer projectId){
        int delete = shardingLogicTableService.delete(projectId);
        if(delete == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }
}
