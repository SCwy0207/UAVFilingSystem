package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Drones;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 存储无人机的详细信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
public interface DronesService extends IService<Drones> {

    IPage<Drones> pageC(Page<Drones> page, LambdaQueryWrapper<Drones> lambdaQueryWrapper);

    Integer getDronesTotal();
}
