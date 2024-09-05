package com.wzxy.uavfilingsystem.controller;

import com.wzxy.uavfilingsystem.common.LoginParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.UsersService;
import com.wzxy.uavfilingsystem.util.JwtUtil;
import com.wzxy.uavfilingsystem.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

        return Result.successToken(200,token);
    }

}
