package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wzxy.uavfilingsystem.entity.Notifications;
import com.wzxy.uavfilingsystem.entity.UserNotifications;
import com.wzxy.uavfilingsystem.service.NotificationsService;
import com.wzxy.uavfilingsystem.service.UserNotificationsService;
import com.wzxy.uavfilingsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户通知状态表，记录每个用户的通知接收和阅读状态 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@RestController
@RequestMapping("/userNotifications")
public class UserNotificationsController {
    @Autowired
    private UserNotificationsService userNotificationsService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private NotificationsService notificationsService;

    @PostMapping("/postNotifications")
    public List<Notifications> postNotifications(@RequestParam String username){
        //通过username获取userid
        Integer userid =usersService.getUserIdByUseranme(username);
        //再由获取的userid获取userNotifications中notificationId
        LambdaQueryWrapper<UserNotifications> userNotificationsQuery= new LambdaQueryWrapper<>();
        userNotificationsQuery.eq(UserNotifications::getUserId,userid).eq(UserNotifications::getIsRead,0);
        List<UserNotifications> userNotifications = userNotificationsService.list(userNotificationsQuery);
        //提取notificationsId:使用 Java Stream API 从 userNotifications 列表中提取出所有的 notificationId，并将其收集到一个新的列表 notificationIds 中
        List<Long> notificationIds =userNotifications.stream()
                .map(UserNotifications::getNotificationId)
                .collect(Collectors.toList());
        //通过notificationId获取到相对应的List<Notifications>返回
        LambdaQueryWrapper<Notifications> notificationsQuery=new LambdaQueryWrapper<>();
        notificationsQuery.in(Notifications::getId,notificationIds);
        return notificationsService.list(notificationsQuery);
    }
    //用户已读
    @GetMapping("/read")
    public boolean read(@RequestParam String username,@RequestParam String id){
        Integer userid =usersService.getUserIdByUseranme(username);
        return userNotificationsService.read(userid,id);
    }

}
