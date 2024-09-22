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
 * 存储用户的基本信息
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Users对象", description="存储用户的基本信息")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户的唯一标识")
    @TableId(value = "UserID", type = IdType.AUTO)
    private Integer userid;

    @ApiModelProperty(value = "用户的电子邮件地址，作为登录名")
    @TableField("Email")
    private String email;

    @ApiModelProperty(value = "用户的密码")
    @TableField("Password")
    private String password;

    @ApiModelProperty(value = "用户的手机号码")
    @TableField("PhoneNumber")
    private String phonenumber;

    @ApiModelProperty(value = "用户的用户名或昵称")
    @TableField("Username")
    private String username;

    @ApiModelProperty(value = "账户创建时间")
    @TableField("Created_At")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField("Last_Login")
    private LocalDateTime lastLogin;

    @ApiModelProperty(value = "用户状态，active为正常，inactive为未激活，suspended为暂停使用")
    @TableField("Status")
    private String status;

    @ApiModelProperty(value = "用户角色，0为管理员，1为用户")
    @TableField("RoleId")
    private Integer roleid;

    @ApiModelProperty(value = "用户头像")
    @TableField("Avatar_Url")
    private String avatar;

}
