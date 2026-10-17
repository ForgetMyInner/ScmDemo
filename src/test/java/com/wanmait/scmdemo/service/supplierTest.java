package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.util.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.TreeSet;


@SpringBootTest
public class supplierTest {
    @Resource
    private SupplierService supplierService;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

//    @Test
//    public void testSupplierReg(){
//
//        AjaxResult bySupplierState = supplierService.findBySupplierState();
//        System.out.println(bySupplierState);
//    }
    @Test
    public void setProductSupplierServiceTest(){
        String s1="hello";
        String s2=new String("hello");
        System.out.println(s1.equals(s2));
    }
    @Resource
    private AttachmentService attachmentService;
    @Test
    public void testSupplierReg(){
//        Supplier supplier=new Supplier();
//        supplier.setSupplierName("钢");
////        System.out.println(supplierService.list());
//        System.out.println("sss"+supplierService.supplierWithAttachAndContact(supplier,1, 2));
////        AjaxResult bySupplierState = supplierService.findBySupplierState();
//        System.out.println(bySupplierState);
        //创建一个TreeSet:
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(12);
        ts.add(3);
        ts.add(7);
        ts.add(9);
        ts.add(3);
        ts.add(16);
        ts.add(5);
        System.out.println(ts.size());
        System.out.println(ts);

    }
    @Test
    public void testEs() {
//        Supplier supplier=new Supplier();
//        supplier.setSupplierName("钢");
////        System.out.println(supplierService.list());
//        System.out.println("sss"+supplierService.supplierWithAttachAndContact(supplier,1, 2));
////        AjaxResult bySupplierState = supplierService.findBySupplierState();
//        System.out.println(bySupplierState);
//
//        elasticsearchRestTemplate.indexOps(Supplier.class).putMapping();
//        System.out.println(supplierService.list());

    }
    
}
