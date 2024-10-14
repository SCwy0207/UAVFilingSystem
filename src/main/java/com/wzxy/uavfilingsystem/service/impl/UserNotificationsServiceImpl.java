package com.wzxy.uavfilingsystem.service.impl;

import com.wzxy.uavfilingsystem.entity.UserNotifications;
import com.wzxy.uavfilingsystem.mapper.UserNotificationsMapper;
import com.wzxy.uavfilingsystem.mapper.UsersMapper;
import com.wzxy.uavfilingsystem.service.NotificationsService;
import com.wzxy.uavfilingsystem.service.UserNotificationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UserNotificationsMapper userNotificationsMapper;
    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Boolean send(String id,String notifyTargets) {
        //分离notifytargets,使用逗号分割
        String[]targets=notifyTargets.split(",");
        //创建一个集合来保存所有符合条件的userId
        List<Long> userIds =new ArrayList<>();
        //处理每一个target
        for(String target: targets){
            target = target.trim();
            if(target.equals("ALL")){
                //查询所有用户的userId
                List<Long> allUsers=usersMapper.findAllUserIds();
                userIds.addAll(allUsers);
            }else if(target.equals("用户")){
                //查询roleId为2的所有的用户的userId
                List<Long> users = usersMapper.findUserIdsByRoleId(2);
                userIds.addAll(users);
            }else if(target.equals("管理员")){
                //查询roleId为1的所有管理员的userId
                List<Long> admins = usersMapper.findUserIdsByRoleId(1);
                userIds.addAll(admins);
            }else{
                //查询具体用户名对应的userId
                Long userId =usersMapper.findUserIdsByUsername(target);
                if(userId!=null){
                    return false;
                }
            }
        }
        //遍历userIds,将每一个用户的通知状态插入 user_notification表中
        for(Long userId:userIds){
            UserNotifications userNotifications=new UserNotifications();
            userNotifications.setUserId(userId);
            userNotifications.setNotificationId(Long.parseLong(id));
            userNotifications.setIsRead(false);
            userNotifications.setReadTime(null);
            //插入user_notifications表
            userNotificationsMapper.insert(userNotifications);
        }
        return true;
    }

    @Override
    public boolean read(Integer userid, String id) {
        return userNotificationsMapper.read(userid,id);
    }
}
