package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.entity.ProductShow;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    List<ProductCategory> selectProduct(String produc);


}
