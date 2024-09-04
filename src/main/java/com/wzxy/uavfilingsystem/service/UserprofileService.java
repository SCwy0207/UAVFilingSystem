package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Userprofile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 存储用户的详细信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
public interface UserprofileService extends IService<Userprofile> {

    IPage<Userprofile> pageC(Page<Userprofile> page, LambdaQueryWrapper<Userprofile> lambdaQueryWrapper);

    boolean removeUserprofileByUserId(Integer userid);
}
