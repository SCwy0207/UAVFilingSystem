package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Droneflightareas;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 存储无人机的适飞区域信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
public interface DroneflightareasService extends IService<Droneflightareas> {

    IPage<Droneflightareas> pageC(Page<Droneflightareas> page, LambdaQueryWrapper<Droneflightareas> lambdaQueryWrapper);
}
