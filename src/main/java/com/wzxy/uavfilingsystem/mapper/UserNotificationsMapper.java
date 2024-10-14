package com.wzxy.uavfilingsystem.mapper;

import com.wzxy.uavfilingsystem.entity.UserNotifications;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户通知状态表，记录每个用户的通知接收和阅读状态 Mapper 接口
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@Mapper
public interface UserNotificationsMapper extends BaseMapper<UserNotifications> {

    boolean read(Integer userid, String id);
}
