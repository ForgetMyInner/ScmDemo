package com.wanmait.scmdemo.mapper;

import com.wanmait.scmdemo.entity.ProductShow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanmait.scmdemo.entity.Supplier;

import java.util.List;

/**
 * <p>
 * 产品展示 Mapper 接口
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface ProductShowMapper extends BaseMapper<ProductShow> {
//    检查编码是否唯一
    boolean existsByProductCode(String productCode);
    //根据供应商查询产品
    List<ProductShow> selectProduct(Supplier supplier);
}
