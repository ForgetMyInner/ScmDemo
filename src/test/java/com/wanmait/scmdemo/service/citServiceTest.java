package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.mapper.CityMapper;
import com.wanmait.scmdemo.mapper.ProvinceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class citServiceTest {
    @Resource
    private CityService cityService;
    @Test
    public void testCity(){
        List<Map<String, Object>> provinceCityCascadeData = cityService.getProvinceCityCascadeData();
        System.out.println(provinceCityCascadeData);
    }
}
