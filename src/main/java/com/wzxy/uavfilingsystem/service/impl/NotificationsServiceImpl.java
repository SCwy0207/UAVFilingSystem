package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Notifications;
import com.wzxy.uavfilingsystem.mapper.NotificationsMapper;
import com.wzxy.uavfilingsystem.service.NotificationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 通知记录表，存储所有通知信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@Service
public class NotificationsServiceImpl extends ServiceImpl<NotificationsMapper, Notifications> implements NotificationsService {
    @Autowired
    private NotificationsMapper notificationsMapper;

    @Override
    public IPage<Notifications> pageC(Page<Notifications> page, LambdaQueryWrapper<Notifications> lambdaQueryWrapper) {
        return notificationsMapper.pageC(page,lambdaQueryWrapper);
    }

    @Override
    public IPage<Notifications> pageTD(Page<Notifications> page, QueryWrapper<Notifications> queryWrapper) {
        return notificationsMapper.pageTD(page,queryWrapper);
    }

    @Override
    public boolean send(String id) {
        //创建一个更新对象
        Notifications notifications = new Notifications();
        notifications.setSendTime(LocalDateTime.now());
        notifications.setScheduledTime(null);
        notifications.setStatus("已发送");
        return notificationsMapper.send(id);
    }
}
