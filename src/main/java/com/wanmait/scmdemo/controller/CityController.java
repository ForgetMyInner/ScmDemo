package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.CityService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 城市 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
@RestController
@RequestMapping("/manage/city")
public class CityController {
    @Resource
    private CityService cityService;

    @GetMapping("/provinceCityCascade")
    public AjaxResult provinceCityCascade(){
        return AjaxResult.success("查询成功").put("provinceCityCascade",cityService.getProvinceCityCascadeData());
    }
}
