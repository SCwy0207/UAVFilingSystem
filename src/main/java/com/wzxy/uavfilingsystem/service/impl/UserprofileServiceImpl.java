package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Userprofile;
import com.wzxy.uavfilingsystem.mapper.UserprofileMapper;
import com.wzxy.uavfilingsystem.service.UserprofileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储用户的详细信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Service
public class UserprofileServiceImpl extends ServiceImpl<UserprofileMapper, Userprofile> implements UserprofileService {
    @Autowired
    private UserprofileMapper userprofileMapper;

    @Override
    public IPage<Userprofile> pageC(Page<Userprofile> page, LambdaQueryWrapper<Userprofile> lambdaQueryWrapper) {
        return userprofileMapper.pageC(page,lambdaQueryWrapper);
    }
}
