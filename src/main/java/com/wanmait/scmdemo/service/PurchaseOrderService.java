package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.PurchaseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 采购订单 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/purchaseOrder")
public interface PurchaseOrderService extends IService<PurchaseOrder> {
    AjaxResult getAllOrder();




}
