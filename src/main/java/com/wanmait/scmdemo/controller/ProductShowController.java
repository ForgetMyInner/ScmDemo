package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.service.ProductShowService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    @GetMapping("/product/list")
    public AjaxResult getProductShow(){

        return AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShowService.list());
    }
    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public AjaxResult addProductShow(@RequestBody ProductShow productShow) {
        System.out.println("添加产品测试");
        productShowService.save(productShow);
        return AjaxResult.success("");
    }

    @PostMapping("/product/uploadPic")
    public AjaxResult uploadPic(MultipartFile file){
        try{
            String path= ResourceUtils.getURL("classpath:").getPath()+"static/uploadImages";
            String fileName = UUID.randomUUID().toString().replaceAll("-","")+"."+ FilenameUtils.getExtension(file.getOriginalFilename());
            file.transferTo(new File(path,fileName));
            return AjaxResult.success("上传成功").put("fileName",fileName);
        }catch (IOException e){
            return AjaxResult.error(e.getMessage());
        }
    }
}
