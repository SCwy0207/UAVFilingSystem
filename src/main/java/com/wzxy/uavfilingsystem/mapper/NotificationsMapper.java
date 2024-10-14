package com.wzxy.uavfilingsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Notifications;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 通知记录表，存储所有通知信息 Mapper 接口
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@Mapper
public interface NotificationsMapper extends BaseMapper<Notifications> {


    IPage<Notifications> pageC(Page<Notifications> page,@Param(Constants.WRAPPER) LambdaQueryWrapper lambdaQueryWrapper);

    IPage<Notifications> pageTD(Page<Notifications> page, @Param(Constants.WRAPPER) QueryWrapper<Notifications> queryWrapper);

    boolean send(String id);
}
