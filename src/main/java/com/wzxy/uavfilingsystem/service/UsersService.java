package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 存储用户的基本信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
public interface UsersService extends IService<Users> {

    IPage<Users> pageC(Page<Users> page, LambdaQueryWrapper<Users> lambdaQueryWrapper);

    Integer getUserTotal();
}
