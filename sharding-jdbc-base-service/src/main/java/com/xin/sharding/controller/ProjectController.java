package com.xin.sharding.controller;

import com.car.resume.common.SysResult;
import com.xin.sharding.model.ShardingProject;
import com.xin.sharding.service.ShardingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: songxiaoyue
 * @Date: 2018/10/11 12:31
 */
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ShardingProjectService shardingProjectService;

    @RequestMapping("list")
    public SysResult<List<ShardingProject>> projectList(){
        List<ShardingProject> all = shardingProjectService.getAll();
        return new SysResult<>(1,"ok",all);
    }

    @RequestMapping("get")
    public ShardingProject get(Integer id){
        return shardingProjectService.select(id);
    }

    @RequestMapping("update")
    public SysResult update(ShardingProject shardingProject){
        int update = shardingProjectService.update(shardingProject);
        if(update == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }
    @RequestMapping("insert")
    public SysResult insert(ShardingProject shardingProject){

        int insert = shardingProjectService.insert(shardingProject);
        if(insert == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

    @RequestMapping("delete")
    public SysResult delete(Integer projectId){
        int delete = shardingProjectService.delete(projectId);
        if(delete == 0){
            return new SysResult(-1,"error");
        }
        return new SysResult(1,"ok");
    }

}
