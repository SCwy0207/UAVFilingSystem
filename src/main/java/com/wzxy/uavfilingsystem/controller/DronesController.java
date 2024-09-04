package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.entity.Drones;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.DronesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 存储无人机的详细信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@RestController
@RequestMapping("/drones")
public class DronesController {
    @Autowired
    private DronesService dronesService;
    //查询无人机数量
    @GetMapping("/getDronesTotal")
    public Integer getDronesTotal(){return dronesService.getDronesTotal();}
    @GetMapping("/list")
    public List<Drones> list(){return dronesService.list();}
    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Drones drone){return dronesService.save(drone);}
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer droneid){return dronesService.removeById(droneid);}

    //根据userid删除用户所有的无人机
    @GetMapping("/deleteByUserid")
    public boolean deleteByUserid(Integer userid){return dronesService.removeDronesByUserId(userid);}
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Drones drone){return dronesService.updateById(drone);}
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Drones drone){return dronesService.saveOrUpdate(drone);}
    //匹配查询(模糊、匹配)
    @PostMapping("/search")
    public List<Drones> search(@RequestBody Drones drones){
        LambdaQueryWrapper<Drones> lambdaQueryWrapper =new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Drones::getDroneid,drones.getDroneid());
        return dronesService.list(lambdaQueryWrapper);
    }
    //查询用户拥有的无人机
    @PostMapping("/searchDronesByUserid")
    public List<Drones> searchDronesByUserid(@RequestBody Drones drones){
        LambdaQueryWrapper<Drones> lambdaQueryWrapper =new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Drones::getUserid,drones.getUserid());
        return dronesService.list(lambdaQueryWrapper);
    }
    //分页查询
    @PostMapping("/listPage")
    public List<Drones> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);
        HashMap param = query.getParam();
        String droneid = (String)param.get("droneid");
        //创建page对象
        Page<Drones> page =new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        //创建LambdaQueryWrapper对象
        LambdaQueryWrapper<Drones> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Drones::getDroneid,droneid);
        //执行分页
        IPage<Drones> result = dronesService.pageC(page,lambdaQueryWrapper);
        System.out.println("total==="+result.getTotal());
        return result.getRecords();
    }
}
