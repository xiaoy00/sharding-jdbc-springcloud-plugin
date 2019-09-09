package com.xin.sharding.controller;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.vo.ShardingDataSourceVO;
import com.xin.sharding.service.ShardingDataSourceService;
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
@RequestMapping("dataSource")
public class DataSourceController {

    @Autowired
    ShardingDataSourceService shardingDataSourceService;

    @RequestMapping("list")
    public Map<String,Object> projectList(Integer projectId){
        List<ShardingDataSourceVO> all = shardingDataSourceService.getAllByProjectId(projectId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        map.put("code",1);
        map.put("count",all.size());
        map.put("msg","");
        return map;
    }

    @RequestMapping("get")
    public SysResult get(Integer dataSourceId){
        ShardingDataSourceVO shardingDataSourceVO = shardingDataSourceService.getById(dataSourceId);
        return new SysResult(1,"ok",shardingDataSourceVO);
    }

    @RequestMapping("update")
    public SysResult update(@RequestBody ShardingDataSourceVO vo){
        int update = shardingDataSourceService.update(vo);
        if(update == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }
    @RequestMapping("add")
    public SysResult add(@RequestBody ShardingDataSourceVO vo){
        int insert = shardingDataSourceService.insert(vo);
        if(insert == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

    @RequestMapping("delete")
    public SysResult delete(Integer dataSourceId){
        int delete = shardingDataSourceService.delete(dataSourceId);
        if(delete == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

}
