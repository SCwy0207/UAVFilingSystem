package com.wzxy.uavfilingsystem.service.impl;

import com.wzxy.uavfilingsystem.entity.Notifications;
import com.wzxy.uavfilingsystem.mapper.NotificationsMapper;
import com.wzxy.uavfilingsystem.service.NotificationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
