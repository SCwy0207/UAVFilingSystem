package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Droneflightareas;
import com.wzxy.uavfilingsystem.entity.Drones;
import com.wzxy.uavfilingsystem.entity.Dronetypes;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.mapper.DronetypesMapper;
import com.wzxy.uavfilingsystem.service.DroneflightareasService;
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
    @Autowired
    private DronetypesMapper dronetypesMapper;
    @Autowired
    private DroneflightareasService droneflightareasService;


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
    public boolean delete(Integer droneid){
        Boolean result1=droneflightareasService.removeDronesByDroneid(droneid);
        Boolean result2=dronesService.removeById(droneid);
        if(result1==true && result2==true){
            return true;
        }else{
            return false;
        }
        }

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
    @PostMapping("/filing1")
    public Result filing1(@RequestBody Drones drones){
        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper =new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Dronetypes::getModel,drones.getModel()).eq(Dronetypes::getProductName,drones.getProductname());
        Dronetypes dronetype = dronetypesMapper.selectOne(lambdaQueryWrapper);
        if (dronetype != null) {
            drones.setManufacturer(dronetype.getManufacturer());
            drones.setCategory(dronetype.getCategory());
            drones.setType(dronetype.getType());
            drones.setEmptyweight(dronetype.getEmptyWeight());
            drones.setMaxtakeoffweight(dronetype.getMaxTakeoffWeight());
            drones.setPurpose(dronetype.getPurpose());
        } else {
            return Result.fail("未找到匹配的型号或产品名称！");
        }
        drones.setFiling(0);
        return Result.success(dronesService.save(drones));
    }

    @GetMapping("/deleteBySerialNumber")
    public Result deleteBySerialNumber(String serialNumber){
        LambdaQueryWrapper<Drones> lambdaQueryWrapper =new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Drones::getSerialnumber,serialNumber);
        boolean isDeleted = dronesService.remove(lambdaQueryWrapper);
        if (isDeleted) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }
    @PostMapping("/filingManagement")
    public Result filingManagement(){
        try{
            List<Drones> dronesUnfiling=dronesService.getDronesUnfiling();
            Long total = (long) dronesUnfiling.size();
            return Result.success(total,dronesUnfiling);
        }catch(Exception e){
            return Result.fail("获取未备案无人机数据失败");
        }
    }
    @PostMapping("/FilingDrones")
    public Result FilingDrones(@RequestBody Drones drones){
        try{
            List<Drones> dronesFiling=dronesService.getDronesByFiling(1);
            Long total = (long) dronesFiling.size();
            return Result.success(total,dronesFiling);
        }catch(Exception e){
            return Result.fail("获取备案无人机数据失败");
        }
    }
    @GetMapping("/accept")
    public Boolean accept(String serialnumber){
        return dronesService.accept(serialnumber);
    }
    @GetMapping("/refuse")
    public Boolean refuse(String serialnumber){
        return dronesService.refuse(serialnumber);
    }
}
