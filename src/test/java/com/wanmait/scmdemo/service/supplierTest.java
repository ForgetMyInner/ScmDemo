package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.util.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class supplierTest {
    @Resource
    private SupplierService supplierService;
    @Test
    public void testSupplierReg(){

        AjaxResult bySupplierState = supplierService.findBySupplierState();
        System.out.println(bySupplierState);
    }

}
