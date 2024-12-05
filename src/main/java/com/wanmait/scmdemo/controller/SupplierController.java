package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.service.SupplierService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 供应商信息表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/supplier")
public class SupplierController {
    @Resource
    private SupplierService supplierService;

    @GetMapping("/list")
    public AjaxResult list(){
        return AjaxResult.success("查询成功").put("name","mazihao").put("suppliers",supplierService.list());
    }
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Supplier supplier){
        if (supplier.getId()!=null){
            supplierService.updateById(supplier);
            return AjaxResult.success("更新成功",supplier);
        }else {
            supplierService.save(supplier);
            return AjaxResult.success("添加成功");
        }
    }
}
