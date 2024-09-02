package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Dronetypes;
import com.wzxy.uavfilingsystem.entity.Userprofile;
import com.wzxy.uavfilingsystem.mapper.DronetypesMapper;
import com.wzxy.uavfilingsystem.service.DronetypesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 记录无人机类型的基础信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@Service
public class DronetypesServiceImpl extends ServiceImpl<DronetypesMapper, Dronetypes> implements DronetypesService {
    @Autowired
    private DronetypesMapper dronetypesMapper;

    @Override
    public IPage<Dronetypes> pageC(Page<Userprofile> page, LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper) {
        return dronetypesMapper.pageC(page,lambdaQueryWrapper);
    }

    @Override
    public boolean modAllowflight(String model, Boolean allowflight) {
        return dronetypesMapper.modAllowflight(model,allowflight);
    }

    @Override
    public boolean update(String model, String registrationname, boolean allowFlight) {
        return dronetypesMapper.update(model,registrationname,allowFlight);
    }
}
