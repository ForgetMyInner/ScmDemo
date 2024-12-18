package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Imgs;
import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.service.ImgsService;
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
import java.util.ArrayList;
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
    @Autowired
    private ImgsService imgsService;

    @GetMapping("/product/list")
    public AjaxResult getProductShow() {
        return AjaxResult.success("查询成功").put("name", "wanmait").put("productShows", productShowService.list());
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public AjaxResult addProductShow(@RequestBody ProductShow productShow) {
        System.out.println("添加产品测试");
        productShowService.save(productShow);
        return AjaxResult.success("");
    }

    @PostMapping("/product/uploadPic")
    public AjaxResult uploadPic(MultipartFile file) {
        try {
            //检查大小和类型
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/uploadImage";
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            file.transferTo(new File(path, fileName));
            return AjaxResult.success("上传成功").put("fileName", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败");
        }

    }


    @PostMapping("/product/uploadPics")
    public AjaxResult uploadPics(MultipartFile[] files, Integer pid) {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/uploadImage";
            List<Imgs> imgsList = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(file.getOriginalFilename());
                file.transferTo(new File(path, fileName));
                // 创建 Imgs 对象，存储文件信息
                Imgs img = new Imgs();
                /**
                  谁用到的id
                 */
                img.setPid(pid);
                img.setType(FilenameUtils.getExtension(file.getOriginalFilename()));
                img.setEnable(true);
                file.transferTo(new File(path, fileName));
            }
            // 保存多个 Imgs 对象到数据库
            imgsService.saveBatch(imgsList);
            return AjaxResult.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败");
        }
    }
}
