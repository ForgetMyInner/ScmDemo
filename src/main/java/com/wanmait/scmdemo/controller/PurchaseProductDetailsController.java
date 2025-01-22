package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.dto.PurchaseNeedPoolDTO;
import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.entity.PurchaseProductDetails;
import com.wanmait.scmdemo.service.ProductSupplierService;
import com.wanmait.scmdemo.service.ProductWarehouseService;
import com.wanmait.scmdemo.service.PurchaseProductDetailsService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 采购明细表----申请（还未购买） 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/purchaseProductDetails")
public class PurchaseProductDetailsController {
    @Autowired
    private PurchaseProductDetailsService purchaseProductDetailsService;
    @Autowired
    private ProductWarehouseService productWarehouseService;
    @Autowired
    private ProductSupplierService productSupplierService;
    @GetMapping("/list")
    public AjaxResult list() {

        List<PurchaseProductDetails> purchaseProductDetailsList = purchaseProductDetailsService.list();
        List<PurchaseNeedPoolDTO> purchaseNeedPoolDTOList= new ArrayList<>();
        for (PurchaseProductDetails details : purchaseProductDetailsList) {
            PurchaseNeedPoolDTO purchaseNeedPoolDTO = new PurchaseNeedPoolDTO();
            LambdaQueryWrapper<ProductWarehouse> warehouseWrapper = new LambdaQueryWrapper<>();
            warehouseWrapper.eq(ProductWarehouse::getId,details.getProductWarehouseId());
            ProductWarehouse warehouse = productWarehouseService.getOne(warehouseWrapper);
            LambdaQueryWrapper<ProductSupplier> productsupplierWrapper = new LambdaQueryWrapper<>();
            productsupplierWrapper.eq(ProductSupplier::getId, details.getProductSupplierId());
            ProductSupplier productSupplier = productSupplierService.getOne(productsupplierWrapper);
            purchaseNeedPoolDTO.setProductWarehouse(warehouse);
            purchaseNeedPoolDTO.setProductSupplier(productSupplier);
            System.out.println(purchaseNeedPoolDTO);
            purchaseNeedPoolDTOList.add(purchaseNeedPoolDTO);
        }
        return AjaxResult.success("查询成功", purchaseNeedPoolDTOList);
    }
    @GetMapping("displayed")
    public AjaxResult displayed(){
        purchaseProductDetailsService.list();

        return AjaxResult.success("查询成功");
    }
}
