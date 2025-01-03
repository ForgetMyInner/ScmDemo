package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.util.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class supplierTest {
    @Resource
    private SupplierService supplierService;
    @Autowired
    private ProductSupplierService productSupplierService;
    @Autowired
    private ProductShowService productShowService;
    @Autowired
    private WarehouseService warehouseService;


//    @Test
//    public void testSupplierReg(){
//
//        AjaxResult bySupplierState = supplierService.findBySupplierState();
//        System.out.println(bySupplierState);
//    }
    @Test
    public void setProductSupplierServiceTest(){
        List<ProductSupplier> list = productSupplierService.list();
        List<Integer> productShowIds = list.stream()
                .map(ProductSupplier::getProductShowId)
                .collect(Collectors.toList());
        List<Integer> SupplierIds = list.stream()
                .map(ProductSupplier::getSupplierId)
                .collect(Collectors.toList());
//        System.out.println(productShowService.listByIds(productShowIds));
//        System.out.println(supplierService.listByIds(SupplierIds));
        List<?> productShows = productShowService.listByIds(productShowIds);
        List<?> suppliers = supplierService.listByIds(SupplierIds);

        Map<String, Object> result = new HashMap<>();
        result.put("productShows", productShows);
        result.put("suppliers", suppliers);

        System.out.println(result);
    }
    @Test
    public void setProductSupplierServiceTest2(){
        List<ProductSupplier> list = productSupplierService.list();
        list.forEach(System.out::println);
    }
    @Test
    public void setProductSupplierServiceTest3(){
        System.out.println(warehouseService.list());
    }


}
