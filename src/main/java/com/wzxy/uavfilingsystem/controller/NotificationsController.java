package com.wzxy.uavfilingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.common.QueryPageParam;
import com.wzxy.uavfilingsystem.common.Result;
import com.wzxy.uavfilingsystem.entity.Notifications;
import com.wzxy.uavfilingsystem.entity.UserNotifications;
import com.wzxy.uavfilingsystem.mapper.NotificationsMapper;
import com.wzxy.uavfilingsystem.service.NotificationsService;
import com.wzxy.uavfilingsystem.service.UserNotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 通知记录表，存储所有通知信息 前端控制器
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-09-22
 */
@RestController
@RequestMapping("/notifications")
public class NotificationsController {
    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private UserNotificationsService userNotificationsService;
    //获取所有通知列表
    @PostMapping("/list")
    public List<Notifications> getNotificationsTotal(){
        return notificationsService.list();
    }
    @PostMapping("/save")
    public Result save(@RequestBody Notifications notifications) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        notifications.setCreatedAt(now);
        notifications.setUpdatedAt(now);
        System.out.println(notifications.getSendTime());
        // 如果前端传来了定时发送时间，则使用它
        if (notifications.getScheduledTime() == null) {
            notifications.setSendTime(now); // 如果没有选择时间，则设置为当前时间
        }

        boolean result = notificationsService.save(notifications);
        if (result) {
            return Result.success();
        }
        return Result.fail();
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody Notifications notifications){
        return notificationsService.updateById(notifications);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam String id){return notificationsService.removeById(id);}
    //分页查询，通过(title)
    @PostMapping("/listPageT")
    public Result listPageT(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String title = (String) param.get("title");
        //创建page对象
        Page<Notifications> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        //创建lambdaQueryWrapper对象
        LambdaQueryWrapper<Notifications> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(title) && !"null".equals(title)) {
            lambdaQueryWrapper.like(Notifications::getTitle, title);
        }
        //执行分页操作
        IPage<Notifications> result = notificationsService.pageC(page, lambdaQueryWrapper);
        return Result.success(result.getTotal(), result.getRecords());
    }
    //倒序分页查询
    @PostMapping("/listPageTD")
    public Result listPageTD(@RequestBody QueryPageParam query){
        //构建QueryWrapper对象
        QueryWrapper<Notifications> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        //创建page对象
        Page<Notifications> page =new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        //执行分页操作
        IPage<Notifications> result = notificationsService.pageTD(page,queryWrapper);
        return Result.success( result.getTotal(),result.getRecords());
    }

    //立即发送
    @GetMapping("/send")
    public Result send(@RequestParam String id,@RequestParam String notifyTargets){
        Boolean result = notificationsService.send(id);
        Boolean result2= userNotificationsService.send(id,notifyTargets);
        if(result&&result2){
            return Result.success("发送成功");
        }
        return Result.fail("发送失败");
    }
    @PostMapping("/saveAndSend")
    public Result saveAndSend(@RequestBody Notifications notifications) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        notifications.setCreatedAt(now);
        notifications.setUpdatedAt(now);

        // 如果前端传来了定时发送时间，则使用它
        if (notifications.getScheduledTime() == null) {
            notifications.setSendTime(now); // 如果没有选择时间，则设置为当前时间

            // 保存通知
            boolean saveResult = notificationsService.save(notifications);
            Long id = notifications.getId(); // 获取自增 ID

            if (saveResult) {
                // 发送通知
                String notifyTargets = notifications.getNotifyTargets(); // 获取通知目标
                Boolean sendResult = notificationsService.send(id.toString()); // 发送通知
                Boolean sendResult2 = userNotificationsService.send(id.toString(), notifyTargets); // 发送给用户通知

                if (sendResult && sendResult2) {
                    return Result.success("保存并发送成功");
                }
                return Result.fail("保存成功，发送失败");
            }
            return Result.fail("保存失败");
        } else {
            // 如果 scheduledTime 有值，只保存，不发送
            boolean saveResult = notificationsService.save(notifications);
            if (saveResult) {
                return Result.success("保存成功，未发送");
            }
            return Result.fail("保存失败");
        }
    }


}
