package com.wzxy.uavfilingsystem.service.impl;

import com.wzxy.uavfilingsystem.entity.UserNotifications;
import com.wzxy.uavfilingsystem.mapper.UserNotificationsMapper;
import com.wzxy.uavfilingsystem.service.UserNotificationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户通知状态表，记录每个用户的通知接收和阅读状态 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@Service
public class UserNotificationsServiceImpl extends ServiceImpl<UserNotificationsMapper, UserNotifications> implements UserNotificationsService {

}
