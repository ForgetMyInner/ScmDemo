package com.wanmait.scmdemo.service;

import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;

/**
 * lzq
 */
@SpringBootTest
public class ProductWarehouseServiceTest {
    @Resource
    private ProductWarehouseService productWarehouseService;

    @Test
    public void testFindAllEnterPersonLike() {
        PageInfo<ProductWarehouse> productWarehousePageInfo = productWarehouseService.search("无意", 1);
        productWarehousePageInfo.getList().forEach(System.out::println);
    }
}
