package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.PurchaseOrder;
import com.wanmait.scmdemo.mapper.PurchaseOrderMapper;
import com.wanmait.scmdemo.service.PurchaseOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 采购订单 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {
    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public AjaxResult getAllOrder() {
        List<PurchaseOrder> list = purchaseOrderMapper.selectList(null);

        return AjaxResult.success("success").put("allOrder",list);
    }
}
