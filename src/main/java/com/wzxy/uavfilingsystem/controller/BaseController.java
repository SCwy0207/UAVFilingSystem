package com.wzxy.uavfilingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 控制用户登录、注册
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@RestController
public class BaseController {
    @GetMapping("/index")
    public String hello(){
        return "hello";
    }}
