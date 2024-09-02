package com.wzxy.uavfilingsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Dronetypes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzxy.uavfilingsystem.entity.Userprofile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 记录无人机类型的基础信息 Mapper 接口
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@Mapper
public interface DronetypesMapper extends BaseMapper<Dronetypes> {

    IPage<Dronetypes> pageC(Page<Userprofile> page,@Param(Constants.WRAPPER) LambdaQueryWrapper lambdaQueryWrapper);

    boolean modAllowflight(String model, Boolean allowflight);

    boolean update(String model, String registrationname, boolean allowFlight);
}
