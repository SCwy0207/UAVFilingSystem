package com.wzxy.uavfilingsystem.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartupTask {
    @PostConstruct
    public void runAtStartup() {
        System.out.println("项目启动时执行此方法");
        // 在这里可以执行你想要的逻辑
    }
}
