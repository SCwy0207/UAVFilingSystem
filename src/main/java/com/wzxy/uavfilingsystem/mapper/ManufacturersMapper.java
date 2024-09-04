package com.wzxy.uavfilingsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Manufacturers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 存储无人机生产厂商的基本信息 Mapper 接口
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@Mapper
public interface ManufacturersMapper extends BaseMapper<Manufacturers> {

    IPage<Manufacturers> pageC(Page<Manufacturers> page, @Param(Constants.WRAPPER) LambdaQueryWrapper lambdaQueryWrapper);

    Integer getManufacturerTotal();
}
