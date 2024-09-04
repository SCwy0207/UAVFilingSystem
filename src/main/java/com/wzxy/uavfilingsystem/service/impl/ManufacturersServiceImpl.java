package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Manufacturers;
import com.wzxy.uavfilingsystem.mapper.ManufacturersMapper;
import com.wzxy.uavfilingsystem.service.ManufacturersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储无人机生产厂商的基本信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@Service
public class ManufacturersServiceImpl extends ServiceImpl<ManufacturersMapper, Manufacturers> implements ManufacturersService {
    @Autowired
    private ManufacturersMapper manufacturersMapper;

    @Override
    public IPage<Manufacturers> pageC(Page<Manufacturers> page, LambdaQueryWrapper<Manufacturers> lambdaQueryWrapper) {
        return manufacturersMapper.pageC(page,lambdaQueryWrapper);
    }

    @Override
    public Integer getManufacturerTotal() {
        return manufacturersMapper.getManufacturerTotal();
    }
}
