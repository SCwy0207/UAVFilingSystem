package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Dronetypes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzxy.uavfilingsystem.entity.Userprofile;

/**
 * <p>
 * 记录无人机类型的基础信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
public interface DronetypesService extends IService<Dronetypes> {

    IPage<Dronetypes> pageC(Page<Userprofile> page, LambdaQueryWrapper<Dronetypes> lambdaQueryWrapper);

    boolean modAllowflight(String model, Boolean allowflight);

    boolean update(String model, String registrationname, boolean allowFlight);
}
