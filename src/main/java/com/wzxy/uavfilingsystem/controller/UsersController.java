package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 存储用户的基本信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/list")
    public List<Users> list(){return usersService.list();}
    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Users user){ return usersService.save(user);}
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer userid){ return usersService.removeById(userid);}
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Users user){ return usersService.updateById(user);}
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Users user){ return usersService.saveOrUpdate(user);}
    //模糊查询
    @PostMapping("/search")
    public List<Users> search(@RequestBody Users user){
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Users::getUsername,user.getUsername());
        return usersService.list(lambdaQueryWrapper);
    }
    //分页
    @PostMapping("/listPage")
    public List<Users> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);
        HashMap param = query.getParam();
        String username = (String)param.get("username");
        System.out.println("name===" + param.get("name"));
        System.out.println("num===" + query.getPageNum());
        System.out.println("size===" + query.getPageSize());

        Page<Users> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Users::getUsername,username);
        //IPage result=userService.pageC(page);
        IPage<Users> result=usersService.pageC(page,lambdaQueryWrapper);
        System.out.println("total===" + result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageT")
    public Result listPageT(@RequestBody QueryPageParam query){
        System.out.println(query);
        HashMap param = query.getParam();
        String username = (String)param.get("username");
        System.out.println("name===" + param.get("name"));
        System.out.println("num===" + query.getPageNum());
        System.out.println("size===" + query.getPageSize());

        Page<Users> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(username) && !"null".equals(username)){
            lambdaQueryWrapper.like(Users::getUsername,username);
        }
        //IPage result=userService.pageC(page);
        IPage<Users> result=usersService.pageC(page,lambdaQueryWrapper);
        System.out.println("totol===" + result.getTotal());
        return Result.success(result.getTotal(),result.getRecords());
    }

}
