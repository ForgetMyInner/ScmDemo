package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanmait.scmdemo.entity.Imgs;
import com.wanmait.scmdemo.entity.SupplierSign;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.service.ImgsService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 附件图片表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/imgs")
public class ImgsController {

    @Resource
    private ImgsService imgsService;
    // 文件上传图片(附件)相关
    @PostMapping("uploadPic")
    public AjaxResult uploadPic(@RequestParam("file") MultipartFile file, @RequestParam("pid") Long pid) {
        try {
            // 检查文件大小
            if (file.getSize() > 2 * 1024 * 1024) {
                return AjaxResult.error("文件大小超过 2MB");
            }
            // 检查文件类型
            String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!StringUtils.hasText(fileType) || (!fileType.equals("jpg") &&!fileType.equals("jpeg") &&!fileType.equals("png") &&!fileType.equals("webp") &&!fileType.equals("gif"))) {
                return AjaxResult.error("不支持的文件类型");
            }
            // 路径
            String path = ResourceUtils.getURL("classpath:").getPath() + "/static/uploadImage";
            // 唯一文件名
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            // 保存
            file.transferTo(new File(path, fileName));
            return AjaxResult.success("上传成功")
                    .put("fileName", fileName)
                    .put("type", "合同附件")
                    .put("sort", 1); // 这里可以根据前端传递的 sort 值进行修改
        } catch (IOException e) {
            return AjaxResult.error("上传失败", e.getMessage()); // 提供更详细的错误信息
        }
    }

    // 多个文件上传
    @PostMapping("/add")
    public AjaxResult addImgs(@RequestBody List<Imgs> imgsList) {
        try {
            // 查询数据库中已有的最大 sort 值
            QueryWrapper<Imgs> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("MAX(sort) as maxSort");
            // 获取查询结果中的最大 sort 值
            Map<String, Object> resultMap = imgsService.getMap(queryWrapper);
            Integer maxSort = 0;
            if (resultMap!= null && resultMap.get("maxSort")!= null) {
                maxSort = (Integer) resultMap.get("maxSort");
            }
            // 遍历前端传入的图片列表，根据情况设置 sort 值
            List<Imgs> updatedImgsList = new ArrayList<>();
            for (Imgs img : imgsList) {
                if (img.getSort() == null || img.getSort() <= maxSort) {
                    // 处理 sort 值为 null 或小于等于现有最大 sort 值的情况
                    maxSort++;
                    img.setSort(maxSort);
                } else {
                    // 处理 sort 值大于现有最大 sort 值的情况
                    maxSort = img.getSort();
                }
                updatedImgsList.add(img);
            }
            boolean result = imgsService.saveBatch(updatedImgsList);
            if (result) {
                return AjaxResult.success("图片信息保存成功", updatedImgsList);
            } else {
                return AjaxResult.error("图片信息保存失败");
            }
        } catch (Exception e) {
            // 更详细的异常处理
            e.printStackTrace();
            return AjaxResult.error("图片信息保存失败", e.getMessage());
        }
    }
}





