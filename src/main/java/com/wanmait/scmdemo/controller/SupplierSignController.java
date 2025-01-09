package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierSign;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.service.SupplierSignService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 供应商签约 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/supplierSign")
public class SupplierSignController {

    @Resource
    private SupplierSignService supplierSignService;

    //供应商签约添加
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SupplierSign supplierSign)//+@RequestBody不加接收不到数据
    {

        //设置供应商签约enable值
        supplierSign.setEnable(true);

        supplierSignService.save(supplierSign);
        return AjaxResult.success("添加成功",supplierSign);
    }
    //通过供应商id调取供应商签约数据
    @GetMapping("getSupplierById")
    public AjaxResult getSupplierById(@RequestParam Integer supplierId) {
        // 调用服务层方法进行业务处理
        SupplierSign supplierSign = supplierSignService.getSupplierById(supplierId);
        if (supplierSign!= null) {
            // 查询到数据，将结果封装在 AjaxResult 中返回
            return AjaxResult.success("查询成功",supplierSign);
        } else {
            // 未查询到数据，返回相应的信息
            return AjaxResult.error("未找到对应的供应商信息");
        }
    }

}
