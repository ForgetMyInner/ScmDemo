package com.wanmait.scmdemo.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.dto.PurchaseNeedPoolDTO;
import com.wanmait.scmdemo.entity.*;
import com.wanmait.scmdemo.util.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class needPoolTest {
    @Autowired
    private PurchaseProductDetailsService purchaseProductDetailsService;
    @Autowired
    private ProductWarehouseService productWarehouseService;
    @Autowired
    private ProductSupplierService productSupplierService;
    @Autowired
    private ProductShowService productShowService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private PurchaseApplyService purchaseApplyService;
    @Autowired
    private SupplierService supplierService;

    @Test
    public void testNeedPoolTest(){

        List<PurchaseProductDetails> purchaseProductDetailsList = purchaseProductDetailsService.list();
        List<PurchaseNeedPoolDTO> purchaseNeedPoolDTOList= new ArrayList<>();
        for (PurchaseProductDetails details : purchaseProductDetailsList) {
            PurchaseNeedPoolDTO purchaseNeedPoolDTO = new PurchaseNeedPoolDTO();

            // 产品在库存
            LambdaQueryWrapper<ProductWarehouse> productWarehouseWrapper = new LambdaQueryWrapper<>();
            productWarehouseWrapper.eq(ProductWarehouse::getId,details.getProductWarehouseId());
            ProductWarehouse productWarehouse = productWarehouseService.getOne(productWarehouseWrapper);

            //产品-----供应商关系表
            LambdaQueryWrapper<ProductSupplier> productsupplierWrapper = new LambdaQueryWrapper<>();
            productsupplierWrapper.eq(ProductSupplier::getId, details.getProductSupplierId());
            ProductSupplier productSupplier = productSupplierService.getOne(productsupplierWrapper);

            //产品表
            LambdaQueryWrapper<ProductShow> productShowWrapper = new LambdaQueryWrapper<>();
            productShowWrapper.eq(ProductShow::getId,productSupplier.getProductShowId());
            ProductShow productShow = productShowService.getOne(productShowWrapper);

            //供应商
            LambdaQueryWrapper<Supplier> supplierWrapper = new LambdaQueryWrapper<>();
            supplierWrapper.eq(Supplier::getId,productSupplier.getSupplierId());
            Supplier supplier = supplierService.getOne(supplierWrapper);

            //仓库表
            LambdaQueryWrapper<Warehouse> warehouseWrapper = new LambdaQueryWrapper<>();
            warehouseWrapper.eq(Warehouse::getId,productWarehouse.getWarehouseId());
            Warehouse warehouse = warehouseService.getOne(warehouseWrapper);

            //采购申请表
            LambdaQueryWrapper<PurchaseApply> purchaseApplyWrapper = new LambdaQueryWrapper<>();
            purchaseApplyWrapper.eq(PurchaseApply::getId,details.getPurchaseApplyId());
            PurchaseApply purchaseApply = purchaseApplyService.getOne(purchaseApplyWrapper);

            purchaseNeedPoolDTO.setProductShow(productShow);
            purchaseNeedPoolDTO.setWarehouse(warehouse);
            purchaseNeedPoolDTO.setPurchaseApply(purchaseApply);
            purchaseNeedPoolDTO.setSupplier(supplier);
            purchaseNeedPoolDTO.setProductWarehouse(productWarehouse);
            purchaseNeedPoolDTO.setProductSupplier(productSupplier);
            purchaseNeedPoolDTOList.add(purchaseNeedPoolDTO);
        }
        System.out.println(purchaseNeedPoolDTOList);
    }

}
