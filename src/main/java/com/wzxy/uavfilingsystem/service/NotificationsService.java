package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Notifications;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 通知记录表，存储所有通知信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
public interface NotificationsService extends IService<Notifications> {

    IPage<Notifications> pageC(Page<Notifications> page, LambdaQueryWrapper<Notifications> lambdaQueryWrapper);

    IPage<Notifications> pageTD(Page<Notifications> page,QueryWrapper<Notifications> queryWrapper);

    boolean send(String id);
}
