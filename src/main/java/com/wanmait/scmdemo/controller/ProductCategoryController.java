package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.ProductCategory;
import com.wanmait.scmdemo.service.ProductCategoryService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/productCategory")
public class ProductCategoryController {

    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public AjaxResult list() {
        return AjaxResult.success(productCategoryService.list());
    }

    /*添加新类型*/
    @PostMapping("add")
    public AjaxResult add(@RequestBody ProductCategory productCategory) {
        return AjaxResult.success(productCategoryService.save(productCategory));
    }

    /*批量删除*/
    @PostMapping("deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Integer> ids) {
        return AjaxResult.success(productCategoryService.removeBatchByIds(ids));
    }
}
