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
 * 通知记录表，存储所有通知信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Notifications对象", description="通知记录表，存储所有通知信息")
public class Notifications implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知ID，自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "通知标题")
    private String title;

    @ApiModelProperty(value = "通知正文")
    private String content;

    @ApiModelProperty(value = "通知目标群体或用户名，逗号分隔")
    private String notifyTargets;

    @ApiModelProperty(value = "定时发送时间，空值表示即时发送")
    private LocalDateTime scheduledTime;

    @ApiModelProperty(value = "发送时间，默认为当前时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "发送状态，待发送或已发送")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
