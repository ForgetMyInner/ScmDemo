package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.PurchaseProductDetails;
import com.wanmait.scmdemo.mapper.PurchaseProductDetailsMapper;
import com.wanmait.scmdemo.service.PurchaseProductDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Override
    public PageInfo<PurchaseProductDetails> purchaseProductDetailsWithAttachAndContact(PurchaseProductDetails purchaseProductDetails, int pageNum, int pageSize) {
        LambdaQueryWrapper<PurchaseProductDetails> purchaseProductDetailsLambdaQueryWrapper=new LambdaQueryWrapper<>(purchaseProductDetails);
        PageHelper.startPage(pageNum, pageSize);

        List<? extends PurchaseProductDetails> PurchaseProductDetails = List.of();
        return new PageInfo<>(PurchaseProductDetails, pageNum);
    }
}
