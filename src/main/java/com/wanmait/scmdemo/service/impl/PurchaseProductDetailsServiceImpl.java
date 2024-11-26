package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.PurchaseProductDetails;
import com.wanmait.scmdemo.mapper.PurchaseProductDetailsMapper;
import com.wanmait.scmdemo.service.PurchaseProductDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购明细表----申请（还未购买） 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseProductDetailsServiceImpl extends ServiceImpl<PurchaseProductDetailsMapper, PurchaseProductDetails> implements PurchaseProductDetailsService {

}
