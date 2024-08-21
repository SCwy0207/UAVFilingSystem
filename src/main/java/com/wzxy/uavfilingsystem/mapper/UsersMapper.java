package com.wzxy.uavfilingsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 存储用户的基本信息 Mapper 接口
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    IPage<Users> pageC(Page<Users> page, @Param(Constants.WRAPPER) LambdaQueryWrapper lambdaQueryWrapper);
}
