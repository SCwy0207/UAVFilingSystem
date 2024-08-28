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
 * 存储无人机生产厂商的基本信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Manufacturers对象", description="存储无人机生产厂商的基本信息")
public class Manufacturers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "厂商的唯一标识")
    @TableId(value = "ManufacturerID", type = IdType.AUTO)
    private Integer manufacturerid;

    @ApiModelProperty(value = "厂商名称")
    @TableField("ManufacturerName")
    private String manufacturername;

    @ApiModelProperty(value = "厂商所在国家")
    @TableField("Country")
    private String country;

    @ApiModelProperty(value = "厂商官网")
    @TableField("Website")
    private String website;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("CreatedAt")
    private LocalDateTime createdat;


}
