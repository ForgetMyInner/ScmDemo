package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.ProductCategory;
import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.mapper.ProductCategoryMapper;
import com.wanmait.scmdemo.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> selectProduct(String dataType) {
        LambdaQueryWrapper<ProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCategory::getProductAttribute, dataType);
        return productCategoryMapper.selectList(queryWrapper);
    }
}
