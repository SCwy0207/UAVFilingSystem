package com.wzxy.uavfilingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
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

    @ApiModelProperty(value = "生产厂商名称")
    @TableField("Manufacturer")
    private String manufacturer;

    @ApiModelProperty(value = "产品名称")
    @TableField("ProductName")
    private String productName;

    @ApiModelProperty(value = "产品型号")
    @TableField("Model")
    private String model;

    @ApiModelProperty(value = "产品类别，如消费级、工业级")
    @TableField("Category")
    private String category;

    @ApiModelProperty(value = "产品类型，如多旋翼、固定翼")
    @TableField("Type")
    private String type;

    @ApiModelProperty(value = "空机重量，单位为公斤")
    @TableField("EmptyWeight")
    private BigDecimal emptyWeight;

    @ApiModelProperty(value = "最大起飞重量，单位为公斤")
    @TableField("MaxTakeoffWeight")
    private BigDecimal maxTakeoffWeight;

    @ApiModelProperty(value = "无人机的使用用途，如摄影、物流")
    @TableField("Purpose")
    private String purpose;

    @ApiModelProperty(value = "是否允许运行起飞，1表示允许，0表示拒绝")
    @TableField("AllowFlight")
    private Boolean allowFlight;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("CreatedAt")
    private LocalDateTime createdAt;
}
