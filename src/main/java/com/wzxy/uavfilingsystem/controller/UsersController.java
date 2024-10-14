package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //获取用户总数
    @GetMapping("/getUsersTotal")
    public Integer getUsersTotal(){ return usersService.getUsersTotal();}
    @GetMapping("/getUsersInActiveTotal")
    public Integer getUsersInActiveTotal(){ return usersService.getUsersInActiveTotal();}
    @GetMapping("/list")
    public List<Users> list(){return usersService.list();}
    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Users user){return usersService.save(user);}
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer userid){ return usersService.removeById(userid);}
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Users user){ return usersService.updateById(user);}
    @PostMapping("/mod2")
    public Result mod2(@RequestBody Users user){
        Boolean result = usersService.updateById(user);
        if(result){
            return Result.success();
        }
        return Result.fail();
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Users user){ return usersService.saveOrUpdate(user);}
    //模糊查询
    @PostMapping("/search")
    public List<Users> search(@RequestBody Users user){
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getUsername())){
            lambdaQueryWrapper.like(Users::getUsername,user.getUsername());
        }
        //lambdaQueryWrapper.eq(Users::getUsername,user.getUsername());
        return usersService.list(lambdaQueryWrapper);
    }
    @PostMapping("/searchDetail")
    public List<Users> searchDetail(@RequestBody Users user){
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getUsername())){
            lambdaQueryWrapper.eq(Users::getUsername,user.getUsername());
        }
        //lambdaQueryWrapper.eq(Users::getUsername,user.getUsername());
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
        lambdaQueryWrapper.eq(Users::getUsername,username);
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
        System.out.println("total===" + result.getTotal());
        return Result.success(result.getTotal(),result.getRecords());
    }
    //通过username获得userid
    @GetMapping("/getUserid")
    public Map<String, Object> getUserid(@RequestParam String username) {
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getUsername, username);

        // 查询匹配的用户
        Users user = usersService.getOne(lambdaQueryWrapper);

        // 准备返回数据
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("userid", user.getUserid());
        } else {
            response.put("error", "用户未找到");
        }

        return response;
    }
    @GetMapping("/get200Userid")
    public Result get200Userid(@RequestParam String username) {
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getUsername, username);

        // 查询匹配的用户
        Users user = usersService.getOne(lambdaQueryWrapper);

        if (user != null) {
            return Result.success(user.getUserid());
        }
        return Result.fail("error,用户未找到");
    }
    @GetMapping("/getUsernameByUserid")
    public Map<String, Object> getUsernameByUserid(@RequestParam Integer userid) {
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getUserid, userid);

        // 查询匹配的用户
        Users user = usersService.getOne(lambdaQueryWrapper);

        // 准备返回数据
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("username", user.getUsername());
        }else{
            response.put("error","系统错误");
        }
        return response;
    }

}
