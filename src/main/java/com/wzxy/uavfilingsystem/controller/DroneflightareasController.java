package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.entity.Droneflightareas;
import com.wzxy.uavfilingsystem.service.DroneflightareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 存储无人机的适飞区域信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@RestController
@RequestMapping("/droneflightareas")
public class DroneflightareasController {
    @Autowired
    private DroneflightareasService droneflightareasService;

    @GetMapping("/list")
    public List<Droneflightareas> droneflightareasList(){return droneflightareasService.list();}

    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Droneflightareas droneflightareas){return droneflightareasService.save(droneflightareas);}
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer areaid){return droneflightareasService.removeById(areaid);}
    //修改
    @PostMapping("/mod")
    public boolean mode(@RequestBody Droneflightareas droneflightareas){return droneflightareasService.updateById(droneflightareas);}
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Droneflightareas droneflightareas){return droneflightareasService.saveOrUpdate(droneflightareas);}
    //匹配查询
    @PostMapping("/search")
    public List<Droneflightareas> search(@RequestBody Droneflightareas droneflightareas){
        LambdaQueryWrapper<Droneflightareas> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Droneflightareas::getAreaid,droneflightareas.getAreaid());
        return droneflightareasService.list(lambdaQueryWrapper);
    }
    //分页
    @PostMapping("/listPage")
    public List<Droneflightareas> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);
        HashMap param = query.getParam();
        String areaid = (String)param.get("areaid");
        System.out.println("num===" + query.getPageNum());
        System.out.println("size===" + query.getPageSize());
        //创建分页对象
        Page<Droneflightareas> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        //创建LambdaQueryWrapper对象
        LambdaQueryWrapper<Droneflightareas> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Droneflightareas::getAreaid,areaid);
        //执行分页操作
        IPage<Droneflightareas> result = droneflightareasService.pageC(page,lambdaQueryWrapper);
        System.out.println("total==="+result.getTotal());
        return result.getRecords();
    }

}
