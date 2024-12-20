package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanmait.scmdemo.aop.LogInfo;
import com.wanmait.scmdemo.entity.ScmDictType;
import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierDTO;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.wanmait.scmdemo.service.SupplierService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Resource
    private ScmDictDataService scmDictDataService;

    //查询所有
    @GetMapping("/list")
    public AjaxResult list(){
        return AjaxResult.success("查询成功").put("name","mazihao").put("suppliers",supplierService.list());
    }
    //查询一级二级三级供应商--为了只给这几类供应商进行操作
    @GetMapping("/qualifiedSupplierList")
    public AjaxResult qualifiedSupplierList() {
        // 创建 QueryWrapper 对象
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        // 定义条件，假设你的列名为 supplier_state
        String[] states = {"一级供应商", "二级供应商", "三级供应商"};
        queryWrapper.in("supplier_state", Arrays.asList(states));
        // 进行条件查询
        List<Supplier> supplierList = supplierService.list(queryWrapper);
        return AjaxResult.success("查询成功").put("name", "Tong").put("suppliers", supplierList);
    }
    //查询注册供应商
    @GetMapping("/regList")
    public AjaxResult regList(){
        return  AjaxResult.success();
    }

    @PostMapping("uploadPic")
    public AjaxResult uploadPic(MultipartFile file){
        try {
            String path = ResourceUtils.getURL("classpath:").getPath()+"/static/uploadImages";
            String fileName = UUID.randomUUID().toString().replaceAll("-","")+"."+ FilenameUtils.getExtension(file.getOriginalFilename());
            file.transferTo(new File(path,fileName));
            return AjaxResult.success("上传成功").put("fileName",fileName);
        } catch (IOException e) {
            return AjaxResult.error("上传失败");
        }
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody SupplierDTO dto){
        supplierService.register(dto);
        System.out.println(dto);
            return AjaxResult.success("添加成功");
    }
    //查询潜在供应商进行审批
    @GetMapping("/innerList")
    public AjaxResult innerList(){
        return supplierService.findBySupplierState();
    }
    //通过供应商的id获取当前供应商
}
