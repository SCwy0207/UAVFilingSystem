package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Drones;
import com.wzxy.uavfilingsystem.mapper.DronesMapper;
import com.wzxy.uavfilingsystem.service.DronesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<Drones> pageC(Page<Drones> page, LambdaQueryWrapper<Drones> lambdaQueryWrapper) {
        return dronesMapper.pageC(page,lambdaQueryWrapper);
    }
}
