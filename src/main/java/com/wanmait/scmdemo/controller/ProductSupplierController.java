package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.service.ProductShowService;
import com.wanmait.scmdemo.service.ProductSupplierService;
import com.wanmait.scmdemo.service.SupplierService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品-----供应商关系表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController

@RequestMapping("/manage/productSupplier")
public class ProductSupplierController {
    @Autowired
    private ProductSupplierService productSupplierService;
    @Autowired
    private ProductShowService productShowService;
    @Autowired
    private SupplierService supplierService;


//    @PostMapping("/add")
//    public AjaxResult addProductSupplier(@RequestBody ProductSupplier productSupplier){
//        ProductSupplier productSupplier1 =new ProductSupplier();
//
//
//        productSupplierService.save(productSupplier1);
//        return AjaxResult.success("添加成功");
//    }

    @GetMapping("SupplierProductList")
    public AjaxResult SupplierProductList(){

        return AjaxResult.success(productSupplierService.list());
    }
}
