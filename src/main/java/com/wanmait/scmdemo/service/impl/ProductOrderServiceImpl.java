package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.ProductOrder;
import com.wanmait.scmdemo.mapper.ProductOrderMapper;
import com.wanmait.scmdemo.service.ProductOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 产品在订单里面的展示 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements ProductOrderService {

}
