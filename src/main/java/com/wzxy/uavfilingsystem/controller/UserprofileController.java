package com.wzxy.uavfilingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Userprofile;
import com.wzxy.uavfilingsystem.entity.Users;
import com.wzxy.uavfilingsystem.service.UserprofileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 存储用户的详细信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
@RestController
@RequestMapping("/userprofile")
public class UserprofileController {
    @Autowired
    private UserprofileService userprofileService;
    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Userprofile userProfile) {
        // 检查 Gender 字段是否为空
        if (userProfile.getGender() == null || userProfile.getGender().trim().isEmpty()) {
            userProfile.setGender("Other"); // 设置默认值
        }
        return userprofileService.save(userProfile);
    }

    //删除
    @GetMapping("/delete")
    public boolean delete(Integer profileid){return userprofileService.removeById(profileid);}
    //修改
    @PostMapping("/mod")
    public boolean update(@RequestBody Userprofile userProfile){return userprofileService.updateById(userProfile);}
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Userprofile userprofile){return userprofileService.saveOrUpdate(userprofile);}

    //匹配查询
    @PostMapping("/search")
    public List<Userprofile> search(@RequestBody Userprofile userprofile){
        LambdaQueryWrapper<Userprofile> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Userprofile::getProfileid,userprofile.getProfileid());
        return userprofileService.list(lambdaQueryWrapper);
    }
    @PostMapping("/searchProfileByUserid")
    public List<Userprofile> searchProfileByUserid(@RequestBody Userprofile userprofile){
        System.out.println("Searching for user profile with User ID: " + userprofile.getUserid());
        LambdaQueryWrapper<Userprofile> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Userprofile::getUserid, userprofile.getUserid());
        return userprofileService.list(lambdaQueryWrapper);
    }

    //分页查询（通过userid）
    @PostMapping("/listPage")
    public List<Userprofile> listPage(@RequestBody QueryPageParam query){
        System.out.println(query);
        HashMap param =query.getParam();
        String useridStr = (String)param.get("userid");
        Integer userid =null;
        //创建page对象
        Page<Userprofile> page =new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        //创建LambdaQueryWrapper对象
        LambdaQueryWrapper<Userprofile> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(useridStr) && !"null".equals(useridStr)){
            userid = Integer.parseInt(useridStr);
            lambdaQueryWrapper.eq(Userprofile::getUserid,userid);
            System.out.println("Querying with User ID: " + userid);
        }
        //执行分页操作
        IPage<Userprofile> result = userprofileService.pageC(page,lambdaQueryWrapper);
        System.out.println("total==="+result.getTotal());
        return result.getRecords();
    }

}
