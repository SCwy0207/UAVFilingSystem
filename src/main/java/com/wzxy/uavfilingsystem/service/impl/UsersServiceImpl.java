package com.wzxy.uavfilingsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.common.ResultCode;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.mapper.UsersMapper;
import com.wzxy.uavfilingsystem.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzxy.uavfilingsystem.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储用户的基本信息 服务实现类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public IPage<Users> pageC(Page<Users> page, LambdaQueryWrapper<Users> lambdaQueryWrapper) {
        return usersMapper.pageC(page,lambdaQueryWrapper);
    }

    @Override
    public Integer getUsersTotal() {
        return usersMapper.getUsersTotal();
    }

    @Override
    public Integer getUsersInActiveTotal() {
        return usersMapper.getUsersInActiveTotal();
    }
    @Override
    public Users login(String username, String password) {
        String encryptedPassword = MD5Utils.MD5(password);
        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getUsername, username)
                .eq(Users::getPassword, encryptedPassword);
        Users user = usersMapper.selectOne(lambdaQueryWrapper);
        if (user == null) {
            System.out.println("用户登录失败，未找到匹配的用户");
            throw new LoginException(ResultCode.USER_LOGIN_ERROR.getMessage());
        }

        return user;
    }

    @Override
    public void updateUserAvatar(String username, String avatarUrl) {
        usersMapper.updateUserAvatar(username,avatarUrl);
    }
    //    //当数据库密码没加密的时候可以用这个破解md5
//    @Override
//    public Users login(String username, String password) {
//        String encryptedPassword = MD5Utils.MD5(password);
//        System.out.println("登录用户名: " + username);
//        System.out.println("加密后的密码: " + encryptedPassword);
//
//        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Users::getUsername, username)
//                .eq(Users::getPassword, encryptedPassword);
//
//        Users user = usersMapper.selectOne(lambdaQueryWrapper);
//        if (user == null) {
//            System.out.println("用户登录失败，未找到匹配的用户");
//            throw new LoginException(ResultCode.USER_LOGIN_ERROR.getMessage());
//        }
//
//        return user;
//    }
}
