package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.service.ProductShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 产品展示 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage")
public class ProductShowController {
    @Autowired
    private ProductShowService productShowService;
    @RequestMapping("/product/list")
    public List<ProductShow> getProductShow(){
        return productShowService.list();
    }
    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProductShow(@RequestBody ProductShow productShow) {
        System.out.println("添加产品测试");
        productShowService.save(productShow);
        return "redirect:/manage/product/add";
    }
}
