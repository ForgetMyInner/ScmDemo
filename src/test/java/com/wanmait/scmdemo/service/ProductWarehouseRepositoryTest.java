package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.esdao.ProductWarehouseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * lzq
 */

@SpringBootTest
public class ProductWarehouseRepositoryTest {
    @Resource
    private ProductWarehouseRepository productWarehouseRepository;

    @Test
    public void testSave() {
        ProductWarehouse productWarehouse = new ProductWarehouse();
        productWarehouse.setEnterPerson("at").setEnterTime(new Date()).setUpdatePerson("无意");
        productWarehouseRepository.save(productWarehouse);
    }

    @Test
    public void testFindById() {
        Optional<ProductWarehouse> productWarehouse = productWarehouseRepository.findById(1);
        System.out.println(productWarehouse);
    }

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(1, 3, Sort.by(Sort.Order.desc("enter_time")));
        Page<ProductWarehouse> all = productWarehouseRepository.findAll(pageable);
        System.out.println("总条数：" + all.getTotalElements());
        System.out.println("总页数：" + all.getTotalPages());
        System.out.println("每页显示条数：" + all.getSize());
        System.out.println("当前页码：" + all.getNumber());
        System.out.println("每页实际条数：" + all.getNumberOfElements());
        List<ProductWarehouse> productWarehouses = all.getContent();
        productWarehouses.forEach(System.out::println);
    }
}
