package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.SupplierDeliverGoods;
import com.wanmait.scmdemo.mapper.SupplierDeliverGoodsMapper;
import com.wanmait.scmdemo.service.SupplierDeliverGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商送货表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierDeliverGoodsServiceImpl extends ServiceImpl<SupplierDeliverGoodsMapper, SupplierDeliverGoods> implements SupplierDeliverGoodsService {

}
