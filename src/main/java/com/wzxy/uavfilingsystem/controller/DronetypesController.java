package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Dronetypes;
import com.wzxy.uavfilingsystem.entity.Manufacturers;
import com.wzxy.uavfilingsystem.entity.Userprofile;
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
 * 记录无人机类型的基础信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@RestController
@RequestMapping("/dronetypes")
public class DronetypesController {
    @Autowired
    private DronetypesService dronetypesService;
    @Autowired
    private ManufacturersService manufacturersService;
    //获取所有无人机型号数量
    @GetMapping("/getDroneTypesTotal")
    public Integer getDroneTypesTotal() {
        return dronetypesService.getDroneTypesTotal();
    }
    @GetMapping("/list")
    public List<Dronetypes> list(){return dronetypesService.list();}

    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Dronetypes dronetype){return dronetypesService.save(dronetype);}
    //删除
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Integer> payload) {
        Integer dronetypeid = payload.get("dronetypeid");
        if (dronetypeid == null) {
            return ResponseEntity.badRequest().body("缺少 dronetypeid 参数");
        }
        boolean result = dronetypesService.removeById(dronetypeid);
        if (result) {
            return ResponseEntity.ok("删除型号成功");
        } else {
            return ResponseEntity.badRequest().body("删除型号失败");
        }
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Dronetypes dronetype){return dronetypesService.updateById(dronetype);}
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Dronetypes dronetype){return dronetypesService.saveOrUpdate(dronetype);}

//    @PostMapping("/update")
//    public boolean update(@RequestBody Dronetypes dronetype){
//        String model = dronetype.getModel();
//        String registrationName = dronetype.getRegistrationname();
//        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Dronetypes::getModel, model).eq(Dronetypes::getRegistrationname, registrationname);
//        Dronetypes dronetypeid = dronetypesService.getOne(lambdaQueryWrapper);
//        Map<String, Object> map = dronetypesService.getMap(lambdaQueryWrapper);
//        return dronetypesService.updateById(map);
//    }
    //查询(根据registrationname查询)
    @PostMapping("/search")
    public List<Dronetypes> search(@RequestBody Dronetypes dronetype){
        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(dronetype.getRegistrationname())){
                lambdaQueryWrapper.eq(Dronetypes::getRegistrationname,dronetype.getRegistrationname());
        }
        return dronetypesService.list(lambdaQueryWrapper);
    }

    //通过无人机型号查询数据库中这个无人机型号的id
    @GetMapping("/getDronetypeidByModel")
    public Map<String, Object> getDronetypeidByModel(@RequestParam String model) {
        // 创建查询条件
        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dronetypes::getModel, model);
        // 查询单个实体
        Dronetypes dronetype = dronetypesService.getOne(lambdaQueryWrapper);
        // 检查查询结果是否为空
        if (dronetype == null) {
            // 如果未找到，返回一个错误信息
            return new HashMap<String, Object>() {{
                put("error", "未找到指定型号的无人机类型");
            }};
        }
        // 提取 dronetypeid 并返回
        Map<String, Object> result = new HashMap<>();
        result.put("dronetypeid", dronetype.getDronetypeid());
        return result;
    }
    //通过查询无人机型号，返回无人机厂商名称
    @GetMapping("/getManufacturerNameByModel")
    public Map<String, Object> getManufacturerNameByModel(@RequestParam String model) {
        // 创建查询条件
        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dronetypes::getModel, model);
        // 查询单个实体
        Dronetypes dronetype = dronetypesService.getOne(lambdaQueryWrapper);
        // 检查查询结果是否为空
       if (dronetype == null) {
           return new HashMap<String, Object>() {{
               put("error", "未找到当前操作无人机厂商");
           }};
       } else {
           Map<String, Object> result = new HashMap<>();
           Integer manufacturerid=dronetype.getManufacturerid();
           LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
           lambdaQueryWrapper2.eq(Manufacturers::getManufacturerid, manufacturerid);
           Manufacturers manufacturer = manufacturersService.getOne(lambdaQueryWrapper2);
           result.put("manufacturername", manufacturer.getManufacturername());
           return result;
       }
    }
    //分页查询(通过registrationname)
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String registrationname = (String) param.get("registrationname");
        LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(registrationname)&&!"null".equals(registrationname)){
            lambdaQueryWrapper.like(Dronetypes::getRegistrationname,registrationname);
        }
        //创建page对象
        Page<Userprofile> page =new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        IPage<Dronetypes> result = dronetypesService.pageC(page,lambdaQueryWrapper);
        return Result.success(result.getTotal(),result.getRecords());
    }
    //修改飞行状态allowflight
    @GetMapping("/modAllowflight")
    public boolean modAllowflight(@RequestParam String model, @RequestParam Boolean allowflight){return dronetypesService.modAllowflight(model,allowflight);}

}
