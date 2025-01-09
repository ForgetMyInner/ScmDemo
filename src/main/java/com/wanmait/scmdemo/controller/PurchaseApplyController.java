package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.dto.PurchaseApplyDTO;
import com.wanmait.scmdemo.entity.PurchaseApply;
import com.wanmait.scmdemo.entity.PurchaseProductDetails;
import com.wanmait.scmdemo.service.PurchaseApplyService;
import com.wanmait.scmdemo.service.PurchaseProductDetailsService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 采购申请（还未购买） 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/purchaseApply")
public class PurchaseApplyController {
    @Autowired
    private PurchaseApplyService purchaseApplyService;
    @Autowired
    private PurchaseProductDetailsService purchaseProductDetailsService;

//    添加采购申请
@PostMapping("/add")
public AjaxResult add(@RequestBody PurchaseApplyDTO formData) {
    PurchaseApply purchaseApply = formData.getPurchaseApply();
    purchaseApplyService.save(purchaseApply);
    List<PurchaseProductDetails> purchaseProductDetailsList = formData.getPurchaseProductDetails();
    for (PurchaseProductDetails details : purchaseProductDetailsList) {
        details.setPurchaseApplyId(purchaseApply.getId());
        details.setProductWarehouseId(purchaseApply.getWarehouseId());
        purchaseProductDetailsService.save(details);
    }
    return AjaxResult.success("采购申请保存成功");
}
}
