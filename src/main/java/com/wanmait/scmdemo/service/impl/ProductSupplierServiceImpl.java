package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.mapper.ProductSupplierMapper;
import com.wanmait.scmdemo.service.ProductSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
