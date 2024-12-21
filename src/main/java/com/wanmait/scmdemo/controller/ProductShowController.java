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
    //根据多选框查询产品权限

    //根据id查询产品
    @GetMapping("/product/selectId")
    public AjaxResult getProductselectId(Integer dataType){
        System.out.println(productShowService.getById(dataType));
        return AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShowService.getById(dataType));
    }
    //查询所有产品
    @PostMapping("/product/list")
    public AjaxResult getProductShowByRoot(@RequestBody ProductShow productShow){
        List<ProductShow>   productShows;
        if (!productShow.getPresentedPermissions()&&!productShow.getSalePermissions()&&!productShow.getPurchasePermissions()){
            productShows  =productShowService.list();
        }
        else
            productShows=productShowService.selectRoot(productShow);

        System.out.println(productShows);

        return AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShows);
    }
    @GetMapping("/product/list")
    public AjaxResult getProductShow(){
        return   AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShowService.list());
    }
    //根据产品类型查找
    @GetMapping("/product/selsctProduct")
    public AjaxResult selsctProduct(String dataType)
    {
        return AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShowService.selectProduct(dataType));
    }
    //根据产品名称查询
    @GetMapping("/product/selsctName")
    public AjaxResult selsctName(String dataType)
    {
        return AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShowService.selectName(dataType));
    }
    //根据品牌查找
    @GetMapping("/product/selsctBrand")
    public AjaxResult selsctBrand(String dataType)
    {
        return AjaxResult.success("查询成功").put("name","wanmait").put("productShows",productShowService.selectBrand(dataType));
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
            //检查大小和类型
            String path=ResourceUtils.getURL("classpath:").getPath()+"static/uploadImage";
            String fileName =UUID.randomUUID().toString().replaceAll("-","")+"."+FilenameUtils.getExtension(file.getOriginalFilename());
            file.transferTo(new File(path,fileName));
            return  AjaxResult.success("上传成功").put("fileName",fileName);
        }catch (IOException e){
            e.printStackTrace();
            return AjaxResult.error("上传失败");
        }

    }
}
