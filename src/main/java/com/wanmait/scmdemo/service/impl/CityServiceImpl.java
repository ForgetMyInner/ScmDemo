package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanmait.scmdemo.entity.City;
import com.wanmait.scmdemo.entity.Province;
import com.wanmait.scmdemo.mapper.CityMapper;
import com.wanmait.scmdemo.mapper.ProvinceMapper;
import com.wanmait.scmdemo.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 城市 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private CityMapper cityMapper;
    @Cacheable(value = "commonCache",key = "'ProvinceCityCascade:'+#ProvinceCityCascade")
    @Override
    public List<Map<String, Object>> getProvinceCityCascadeData() {
        List<Province> provinces = provinceMapper.selectList(null);
        List<Map<String, Object>> cascadeData = new ArrayList<>();

        for (Province province : provinces) {
            Map<String, Object> provinceMap = new HashMap<>();
            provinceMap.put("value", province.getId());
            provinceMap.put("label", province.getName());
            provinceMap.put("children", getCitiesByProvinceId(province.getId()));
            cascadeData.add(provinceMap);
        }

        return cascadeData;
    }

    private List<Map<String, Object>> getCitiesByProvinceId(int provinceId) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("province_id", provinceId);
        List<City> cities = cityMapper.selectList(queryWrapper);

        return cities.stream().map(city -> {
            Map<String, Object> cityMap = new HashMap<>();
            cityMap.put("value", city.getId());
            cityMap.put("label", city.getName());
            return cityMap;
        }).collect(Collectors.toList());
    }
}
