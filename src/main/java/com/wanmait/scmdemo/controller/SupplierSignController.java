package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.SupplierSign;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.service.SupplierSignService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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


}
