package com.wanmait.scmdemo.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.dto.PurchaseNeedPoolDTO;
import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.entity.PurchaseProductDetails;
import com.wanmait.scmdemo.util.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class needPoolTest {
    @Autowired
    private PurchaseProductDetailsService purchaseProductDetailsService;
    @Autowired
    private ProductWarehouseService productWarehouseService;
    @Autowired
    private ProductSupplierService productSupplierService;

    @Test
    public void testNeedPoolTest(){
        List<PurchaseProductDetails> purchaseProductDetailsList = purchaseProductDetailsService.list();

        for (PurchaseProductDetails details : purchaseProductDetailsList) {
            PurchaseNeedPoolDTO purchaseNeedPoolDTO = new PurchaseNeedPoolDTO();
            Integer id = details.getId();
            LambdaQueryWrapper<ProductWarehouse> warehouseWrapper = new LambdaQueryWrapper<>();
            warehouseWrapper.eq(ProductWarehouse::getId,details.getProductWarehouseId());
            ProductWarehouse warehouse = productWarehouseService.getOne(warehouseWrapper);
            LambdaQueryWrapper<ProductSupplier> supplierWrapper = new LambdaQueryWrapper<>();
            supplierWrapper.eq(ProductSupplier::getId, details.getProductSupplierId());
            ProductSupplier supplier = productSupplierService.getOne(supplierWrapper);
            purchaseNeedPoolDTO.setProductWarehouse(warehouse);
            purchaseNeedPoolDTO.setProductSupplier(supplier);
        }

    }

}
