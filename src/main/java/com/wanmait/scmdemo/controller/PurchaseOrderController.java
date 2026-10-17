package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.PurchaseOrderService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 采购订单 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/purchaseOrder")
public class PurchaseOrderController {
    @Resource
    private PurchaseOrderService purchaseOrderService;

    //查询所有采购订单
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success("查询成功").put("name","tongjilei").put("purchaseOrderList",purchaseOrderService.list());
    }
    //查询所有员工
    @GetMapping("/list2")
    public AjaxResult listby()
    {

        return AjaxResult.success("查询成功",purchaseOrderService.list());
    }




}
