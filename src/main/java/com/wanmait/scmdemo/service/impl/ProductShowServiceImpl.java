package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.mapper.ProductShowMapper;
import com.wanmait.scmdemo.service.ProductShowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品展示 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductShowServiceImpl extends ServiceImpl<ProductShowMapper, ProductShow> implements ProductShowService {
    @Autowired
    private ProductShowMapper  productShowMapper;
    @Override
    public boolean save(ProductShow entity) {
        entity.setProductCode(generateProductCode());
        entity.setEnterTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setEnable(true);
        return super.save(entity);
    }
//    从数据库查询然后生成一个唯一的编码
    private String generateProductCode() {
        String productCode;
        do {
            productCode = String.valueOf(System.currentTimeMillis());
        } while (productShowMapper.existsByProductCode(productCode));
        return productCode;
    }

    @Override
    //根据品牌查找
    public List<ProductShow> selectProduct(String produc) {
        LambdaQueryWrapper<ProductShow> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductShow::getProductType,produc);
        return productShowMapper.selectList(queryWrapper);
    }
    //根据品牌查找
    public List<ProductShow> selectBrand(String brand) {
        LambdaQueryWrapper<ProductShow> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductShow::getProductBrand,brand);
        return productShowMapper.selectList(queryWrapper);
    }
    //根据品牌查找
    public List<ProductShow> selectName(String name) {
        LambdaQueryWrapper<ProductShow> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductShow::getProductName,name);
        return productShowMapper.selectList(queryWrapper);
    }
}
