package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
public interface CityService extends IService<City> {
    List<Map<String, Object>> getProvinceCityCascadeData();

}
