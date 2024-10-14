package com.wzxy.uavfilingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户通知状态表，记录每个用户的通知接收和阅读状态
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserNotifications对象", description="用户通知状态表，记录每个用户的通知接收和阅读状态")
public class UserNotifications implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录ID，自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "通知ID，关联notifications表")
    private Long notificationId;

    @ApiModelProperty(value = "是否已读，0表示未读，1表示已读")
    private Boolean isRead;

    @ApiModelProperty(value = "已读时间，未读时为空")
    private LocalDateTime readTime;

}
