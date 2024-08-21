package com.wzxy.uavfilingsystem.entity;

import java.math.BigDecimal;
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
 * 存储无人机的详细信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Drones对象", description="存储无人机的详细信息")
public class Drones implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "无人机的唯一标识")
    @TableId(value = "DroneID", type = IdType.AUTO)
    private Integer droneid;

    @ApiModelProperty(value = "外键，关联到Users表的UserID，标识无人机的所有者")
    @TableField("UserID")
    private Integer userid;

    @ApiModelProperty(value = "生产厂商名称")
    @TableField("Manufacturer")
    private String manufacturer;

    @ApiModelProperty(value = "产品名称")
    @TableField("ProductName")
    private String productname;

    @ApiModelProperty(value = "产品型号")
    @TableField("Model")
    private String model;

    @ApiModelProperty(value = "产品序列号或出厂序号")
    @TableField("SerialNumber")
    private String serialnumber;

    @ApiModelProperty(value = "无人机的唯一产品识别码")
    @TableField("UniqueID")
    private String uniqueid;

    @ApiModelProperty(value = "产品类别，如消费级、工业级")
    @TableField("Category")
    private String category;

    @ApiModelProperty(value = "产品类型，如多旋翼、固定翼")
    @TableField("Type")
    private String type;

    @ApiModelProperty(value = "空机重量，单位为公斤")
    @TableField("EmptyWeight")
    private BigDecimal emptyweight;

    @ApiModelProperty(value = "最大起飞重量，单位为公斤")
    @TableField("MaxTakeoffWeight")
    private BigDecimal maxtakeoffweight;

    @ApiModelProperty(value = "无人机的使用用途，如摄影、物流")
    @TableField("Purpose")
    private String purpose;

    @ApiModelProperty(value = "正面照片的文件路径")
    @TableField("FrontPhoto")
    private String frontphoto;

    @ApiModelProperty(value = "产品序列号照片的文件路径")
    @TableField("SerialPhoto")
    private String serialphoto;


}
