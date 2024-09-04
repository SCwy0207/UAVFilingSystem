package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Manufacturers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 存储无人机生产厂商的基本信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
public interface ManufacturersService extends IService<Manufacturers> {

    IPage<Manufacturers> pageC(Page<Manufacturers> page, LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper);

    Integer getManufacturerTotal();
}
