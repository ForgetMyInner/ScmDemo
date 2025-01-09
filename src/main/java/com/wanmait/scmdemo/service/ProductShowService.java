package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ProductShow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.entity.Supplier;

import java.util.List;

/**
 * <p>
 * 产品展示 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface ProductShowService extends IService<ProductShow> {
    //根据产品类型查询
    List<ProductShow> selectProduct(String produc);
    //根据产品品牌查询
    List<ProductShow> selectBrand(String brand);
    //根据产品名称查询
    List<ProductShow> selectName(String name);

    List<ProductShow> selectRoot(ProductShow productShow);
    //根据产品类型查询
    List<ProductShow> selectProduct(Supplier supplier);

}
