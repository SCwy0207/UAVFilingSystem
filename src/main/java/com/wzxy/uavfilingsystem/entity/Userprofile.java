package com.wzxy.uavfilingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 存储用户的详细信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Userprofile对象", description="存储用户的详细信息")
public class Userprofile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户信息的唯一标识")
    @TableId(value = "ProfileID", type = IdType.AUTO)
    private Integer profileid;

    @ApiModelProperty(value = "外键，关联到Users表的UserID")
    @TableField("UserID")
    private Integer userid;

    @ApiModelProperty(value = "用户的名字")
    @TableField("FirstName")
    private String firstname;

    @ApiModelProperty(value = "用户的姓氏")
    @TableField("LastName")
    private String lastname;

    @ApiModelProperty(value = "用户的性别")
    @TableField("Gender")
    private String gender;

    @ApiModelProperty(value = "用户的出生日期")
    @TableField("DateOfBirth")
    private LocalDate dateofbirth;

    @ApiModelProperty(value = "用户的个人简介或个性签名")
    @TableField("Bio")
    private String bio;


}
