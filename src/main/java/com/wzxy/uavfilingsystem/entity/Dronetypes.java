package com.wzxy.uavfilingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 记录无人机类型的基础信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Dronetypes对象", description="记录无人机类型的基础信息")
public class Dronetypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "无人机类型的唯一标识")
    @TableId(value = "DroneTypeID", type = IdType.AUTO)
    private Integer dronetypeid;

    @ApiModelProperty(value = "外键，关联到Manufacturers表的ManufacturerID")
    @TableField("ManufacturerID")
    private Integer manufacturerid;

    @ApiModelProperty(value = "产品型号")
    @TableField("Model")
    private String model;

    @ApiModelProperty(value = "无人机备案名称，支持中文和英文")
    @TableField("RegistrationName")
    private String registrationname;

    @ApiModelProperty(value = "是否允许运行起飞，1表示允许，0表示拒绝")
    @TableField("AllowFlight")
    private Boolean allowflight;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("CreatedAt")
    private LocalDateTime createdat;


}
