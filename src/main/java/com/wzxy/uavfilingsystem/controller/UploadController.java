package com.wzxy.uavfilingsystem.controller;

import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.service.DronesService;
import com.wzxy.uavfilingsystem.service.UsersService;
import com.wzxy.uavfilingsystem.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Transactional
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;
    @Autowired
    private UsersService usersService;
    @Autowired
    private DronesService dronesService;

    /**
     * 上传用户头像
     * @param avatar 上传的文件
     * @param username 用户的唯一标识符
     * @return 上传后的文件访问URL
     */
    @PostMapping("/avatar")
    public Result uploadAvatar(@RequestParam("avatar") MultipartFile avatar, @RequestParam("username") String username) {
        if (avatar.isEmpty()) {
            return Result.fail("上传文件不能为空");
        }

        try {
            // 使用 username 生成唯一的文件名，确保每个用户的头像不同
//            String fileName = "avatar_" + username + "_" + System.currentTimeMillis() + ".png";
            String fileName = username;
            String avatarUrl = qiniuUtils.uploadFile(avatar.getInputStream(), fileName);

            // 这里可以更新数据库中的头像URL，保存到用户信息表中
            usersService.updateUserAvatar(username, avatarUrl);

            return Result.success("头像上传成功，访问地址: " + avatarUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("头像上传失败: " + e.getMessage());
        }
    }

    @PostMapping("/filing")
    public Result uploadDrone(@RequestParam("drone1") MultipartFile droneImage1, @RequestParam("sn") String sn) {
        if (droneImage1.isEmpty()) {
            return Result.fail("上传文件不能为空");
        }
        System.out.println(droneImage1.getOriginalFilename());
        try {
            // 处理第一个图片
            String frontPhotoName = sn + "_FrontPhoto";
            String frontPhotoUrl = qiniuUtils.uploadFiling(droneImage1.getInputStream(), frontPhotoName);
            System.out.println("FrontPhoto URL: " + frontPhotoUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("图片上传失败: " + e.getMessage());
        }

        return Result.success("图片上传成功！");
    }
    @PostMapping("/filing2")
    public Result uploadDrone2( @RequestParam("drone2") MultipartFile droneImage2, @RequestParam("sn") String sn) {
        if (droneImage2.isEmpty()) {
            return Result.fail("上传文件不能为空");
        }

        try {
            // 处理第二个图片
            String serialPhotoName = sn + "_SerialPhoto";
            String serialPhotoUrl = qiniuUtils.uploadFiling(droneImage2.getInputStream(), serialPhotoName);
            System.out.println("SerialPhoto URL: " + serialPhotoUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("图片上传失败: " + e.getMessage());
        }

        return Result.success("图片上传成功！");
    }
}
