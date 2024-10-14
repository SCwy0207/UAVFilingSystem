package com.wzxy.uavfilingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wzxy.uavfilingsystem.common.LoginParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.UserRegistrationDTO;
import com.wzxy.uavfilingsystem.entity.Userprofile;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.UserprofileService;
import com.wzxy.uavfilingsystem.service.UsersService;
import com.wzxy.uavfilingsystem.util.JwtUtil;
import com.wzxy.uavfilingsystem.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制用户登录、注册
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Api(tags = "登录模块")
@RestController
public class BaseController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserprofileService userprofileService;
    //登录操作
    @ApiOperation(value = "登录", notes = "需要提供用户名和密码")
    @PostMapping(value = "/login")
    public Result login(@RequestBody(required = false) LoginParam param,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password) {
        String loginUsername = username != null ? username : param.getUsername();
        String loginPassword = password != null ? password : MD5Utils.MD5(param.getPassword());

        // 对密码进行加密与数据库进行比对
        Users user = usersService.login(loginUsername, loginPassword);

        // 新增token 相关代码
        Map<String,Object> map = new HashMap<>();
        map.put("userId", user.getUserid());
        map.put("username", user.getUsername());
        map.put("roleId", user.getRoleid());
        //token验证
        String token = JwtUtil.generateJwt(map);
        usersService.setStatus(user.getUserid(),"inactive");
        return Result.successToken(200,token);
    }
    @GetMapping("/logout")
    public Result logout(@RequestParam(value = "username", required = false) String username){
        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getUsername, username);
        Users user = usersService.getOne(lambdaQueryWrapper);
        usersService.setStatus(user.getUserid(),"active");
        return Result.success();
    }
//    @PostMapping("/register/userSave")
//    public boolean save(@RequestBody Users user){
//        user.setRoleid(2);
//        return usersService.save(user);
//    }
//
//    @GetMapping("/register/getUserid")
//    public Map<String, Object> getUserid(@RequestParam String username) {
//        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Users::getUsername, username);
//
//        // 查询匹配的用户
//        Users user = usersService.getOne(lambdaQueryWrapper);
//
//        // 准备返回数据
//        Map<String, Object> response = new HashMap<>();
//        if (user != null) {
//            response.put("userid", user.getUserid());
//        } else {
//            response.put("error", "用户未找到");
//        }
//
//        return response;
//    }
//
//    @PostMapping("/register/userprofileSave")
//    public boolean userprofileSave(@RequestBody Userprofile userprofile){
//        return userprofileService.save(userprofile);
//    }

    //用户注册操作“@Transactional”启用事务回滚操作
    @PostMapping("/register/userSave")
    @Transactional
    public Result register(@RequestBody UserRegistrationDTO registrationDTO) {
        try {
            // 保存用户数据
            Users user = registrationDTO.getUser();
            user.setRoleid(2);
            boolean userSaved = usersService.save(user);
            if (!userSaved) {
                throw new RuntimeException("用户表数据保存失败");
            }

            // 获取用户ID
            LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Users::getUsername, user.getUsername());
            Users savedUser = usersService.getOne(lambdaQueryWrapper);
            Integer userId = savedUser.getUserid();

            // 保存用户详情
            Userprofile userprofile = registrationDTO.getUserprofile();
            userprofile.setUserid(userId);
            boolean userprofileSaved = userprofileService.save(userprofile);
            if (!userprofileSaved) {
                throw new RuntimeException("用户详情表数据保存失败");
            }

            return Result.success("用户注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("用户注册失败");
        }
    }


}
