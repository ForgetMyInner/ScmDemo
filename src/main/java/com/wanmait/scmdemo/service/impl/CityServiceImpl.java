package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.City;
import com.wanmait.scmdemo.mapper.CityMapper;
import com.wanmait.scmdemo.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
