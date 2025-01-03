package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.mapper.ProductShowMapper;
import com.wanmait.scmdemo.mapper.ProductSupplierMapper;
import com.wanmait.scmdemo.mapper.SupplierMapper;
import com.wanmait.scmdemo.service.ProductSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品-----供应商关系表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSupplierServiceImpl extends ServiceImpl<ProductSupplierMapper, ProductSupplier> implements ProductSupplierService {
    @Autowired
    private ProductSupplierMapper productSupplierMapper;
    @Autowired
    private ProductShowMapper productShowMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Override
    public List<ProductSupplier> list() {
        LambdaQueryWrapper<ProductSupplier> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                ProductSupplier::getId,
                ProductSupplier::getProductShowId,
                ProductSupplier::getSupplierId,
                ProductSupplier::getProductUnitPrice,
                ProductSupplier::getProductPrice,
                ProductSupplier::getDiscountRate,
                ProductSupplier::getTax
        );
        List<ProductSupplier> list = productSupplierMapper.selectList(wrapper);
        for (ProductSupplier productSupplier : list) {
            productSupplier.setProductShow(productShowMapper.selectById(productSupplier.getProductShowId()));
            productSupplier.setSupplier(supplierMapper.selectById(productSupplier.getSupplierId()));
        }
        return list;
    }
}
