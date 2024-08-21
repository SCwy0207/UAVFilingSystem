package com.wzxy.uavfilingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 存储无人机的适飞区域信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Droneflightareas对象", description="存储无人机的适飞区域信息")
public class Droneflightareas implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "适飞区域的唯一标识")
    @TableId(value = "AreaID", type = IdType.AUTO)
    private Integer areaid;

    @ApiModelProperty(value = "外键，关联到Drones表的DroneID")
    @TableField("DroneID")
    private Integer droneid;

    @ApiModelProperty(value = "主要适飞区域")
    @TableField("PrimaryArea")
    private String primaryarea;

    @ApiModelProperty(value = "第二适飞区域")
    @TableField("SecondaryArea")
    private String secondaryarea;

    @ApiModelProperty(value = "第三适飞区域")
    @TableField("TertiaryArea")
    private String tertiaryarea;

    @ApiModelProperty(value = "特殊适飞区域")
    @TableField("SpecialArea")
    private String specialarea;


}
