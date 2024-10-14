package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Droneflightareas;
import com.wzxy.uavfilingsystem.mapper.DroneflightareasMapper;
import com.wzxy.uavfilingsystem.service.DroneflightareasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储无人机的适飞区域信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Service
public class DroneflightareasServiceImpl extends ServiceImpl<DroneflightareasMapper, Droneflightareas> implements DroneflightareasService {
    @Autowired
    private DroneflightareasMapper droneflightareasMapper;

    @Override
    public IPage<Droneflightareas> pageC(Page<Droneflightareas> page, LambdaQueryWrapper<Droneflightareas> lambdaQueryWrapper) {
        return droneflightareasMapper.pageC(page,lambdaQueryWrapper);
    }

    @Override
    public Boolean removeDronesByDroneid(Integer droneid) {
        return droneflightareasMapper.removeDronesByDroneid(droneid);
    }
}
