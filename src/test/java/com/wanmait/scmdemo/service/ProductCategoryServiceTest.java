package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductCategoryServiceTest {

    @Resource
    private ProductCategoryService productCategoryService;

    @Test
    public void testList() {
        List<ProductCategory> list = productCategoryService.list();
        list.forEach(System.out::println);
    }

    /*测试批量删除*/
    @Test
    public void testDeleteBatch() {
        productCategoryService.removeBatchByIds(Arrays.asList(1, 2, 3));
    }
}
