package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Dronetypes;
import com.wzxy.uavfilingsystem.entity.Manufacturers;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.DronetypesService;
import com.wzxy.uavfilingsystem.service.ManufacturersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 存储无人机生产厂商的基本信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@RestController
@RequestMapping("/manufacturers")
public class ManufacturersController {
    @Autowired
    private ManufacturersService manufacturersService;
    @Autowired
    private DronetypesService dronetypesService;
    //获取无人机厂商总数
    @GetMapping("/getManufacturersTotal")
    public Integer getManufacturersTotal(){return manufacturersService.getManufacturersTotal();}

    @GetMapping("/list")
    public List<Manufacturers> list(){return manufacturersService.list();}
    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Manufacturers manufacturer){return manufacturersService.save(manufacturer);}

    //删除(需要再返回提醒当前删除厂商旗下的注册无人机种类数量，确认无误，再删除)
    @CrossOrigin(origins = "http://localhost:8088")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Integer> payload){
        Integer manufacturerid = payload.get("manufacturerid");
        if (manufacturerid == null) {
            return ResponseEntity.badRequest().body("缺少 manufacturerid 参数");
        }
        boolean result = manufacturersService.removeById(manufacturerid);
        if (result){
            return ResponseEntity.ok("删除厂商成功");
        }else {
            return ResponseEntity.badRequest().body("删除厂商失败");
        }
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Manufacturers manufacturer){return manufacturersService.updateById(manufacturer);}

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Manufacturers manufacturer){return manufacturersService.saveOrUpdate(manufacturer);}

    //根据manufacturename查询
    @PostMapping("search")
    public List<Manufacturers> search(@RequestBody Manufacturers manufacturer){
        LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(manufacturer.getManufacturername())) {
            lambdaQueryWrapper.eq(Manufacturers::getManufacturername, manufacturer.getManufacturername());
        }
        return manufacturersService.list(lambdaQueryWrapper);
    }

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String manufacturername = (String)param.get("manufacturername");
        //创建page对象
        Page<Manufacturers> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(manufacturername) && !"null".equals(manufacturername)){
            lambdaQueryWrapper.eq(Manufacturers::getManufacturername,manufacturername);
        }
        //执行分页操作
        IPage<Manufacturers> result=manufacturersService.pageC(page,lambdaQueryWrapper);
        return Result.success(result.getTotal(),result.getRecords());
    }
    //嵌套查询通过manufacturerid查询dronetypes
    @PostMapping("/postDroneTypes")
    public Map<String, Object> postDroneTypes(@RequestParam String manufacturerid) {
        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dronetypes::getManufacturerid, manufacturerid);
        // 执行查询并获取结果
        List<Dronetypes> droneTypesList = dronetypesService.list(lambdaQueryWrapper);
        // 将结果封装到 Map 中
        Map<String, Object> result = new HashMap<>();
        result.put("droneTypes", droneTypesList);
        System.out.println("sucess");
        return result;
    }

    //通过查询manufactrername来查询对应的manufacturerid，从而再查询dronetypes
    @PostMapping("/postDroneTypesByManufactrername")
    public Map<String, Object> postDroneTypesByManufactrername(@RequestParam String manufacturername) {
        LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Manufacturers::getManufacturername, manufacturername);
        Manufacturers manufacturer = manufacturersService.getOne(lambdaQueryWrapper);
        if (manufacturer != null) {
            // 执行查询并获取结果
            List<Dronetypes> droneTypesList = dronetypesService.list(new LambdaQueryWrapper<Dronetypes>().eq(Dronetypes::getManufacturerid, manufacturer.getManufacturerid()));
            // 将结果封装到 Map 中
            Map<String, Object> result = new HashMap<>();
            result.put("droneTypes", droneTypesList);
            return result;
        }else return null;
    }
    @GetMapping("/getManufactureridByManufacturerName")
    public Map<String, Object> getManufacturerid(@RequestParam String manufacturername) {
        LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Manufacturers::getManufacturername, manufacturername);

        // 查询匹配的用户
        Manufacturers manufacturer = manufacturersService.getOne(lambdaQueryWrapper);

        // 准备返回数据
        Map<String, Object> response = new HashMap<>();
        if (manufacturer != null) {
            response.put("manufacturerid", manufacturer.getManufacturerid());
        } else {
            response.put("error", "厂商未找到");
        }

        return response;
    }

}
