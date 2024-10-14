package com.wzxy.uavfilingsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzxy.uavfilingsystem.entity.Drones;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 存储无人机的详细信息 服务类
 * </p>
 *
 * @author 文王寰宇
 * @since 2024-08-19
 */
public interface DronesService extends IService<Drones> {

    IPage<Drones> pageC(Page<Drones> page, LambdaQueryWrapper<Drones> lambdaQueryWrapper);

    Integer getDronesTotal();

    boolean removeDronesByUserId(Integer userid);

    List<Drones> getDronesUnfiling();

    void updateFrontPhotoUrl(String sn, String frontPhotoUrl);

    void updateSerialPhotoUrl(String sn, String serialPhotoUrl);

    List<Drones> getDronesByFiling(int i);

    Boolean refuse(String serialnumber);

    Boolean accept(String serialnumber);
}
