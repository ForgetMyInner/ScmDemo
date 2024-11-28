package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.mapper.ProductWarehouseMapper;
import com.wanmait.scmdemo.service.ProductWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 产品在库存 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductWarehouseServiceImpl extends ServiceImpl<ProductWarehouseMapper, ProductWarehouse> implements ProductWarehouseService {

}
