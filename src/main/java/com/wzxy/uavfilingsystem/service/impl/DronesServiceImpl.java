package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Droneflightareas;
import com.wzxy.uavfilingsystem.entity.Drones;
import com.wzxy.uavfilingsystem.mapper.DroneflightareasMapper;
import com.wzxy.uavfilingsystem.mapper.DronesMapper;
import com.wzxy.uavfilingsystem.service.DronesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 存储无人机的详细信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Service
public class DronesServiceImpl extends ServiceImpl<DronesMapper, Drones> implements DronesService {
    @Autowired
    private DronesMapper dronesMapper;
    @Autowired
    private DroneflightareasMapper droneflightareasMapper;

    @Override
    public IPage<Drones> pageC(Page<Drones> page, LambdaQueryWrapper<Drones> lambdaQueryWrapper) {
        return dronesMapper.pageC(page,lambdaQueryWrapper);
    }

    @Override
    public Integer getDronesTotal() {
        return dronesMapper.getDronesTotal();
    }

    @Override
    public boolean removeDronesByUserId(Integer userid) {
        // 获取所有与 userid 相关的 drone 数据
        List<Drones> drones = dronesMapper.getDronesByUserId(userid);

        // 遍历 drones 并删除对应的 droneflightareas 记录
        for (Drones drone : drones) {
            int droneId = drone.getDroneid();
            droneflightareasMapper.deleteByDroneId(droneId);
        }

        // 删除所有与 userid 相关的 drone 数据
        return dronesMapper.removeDronesByUserId(userid);
    }

}
