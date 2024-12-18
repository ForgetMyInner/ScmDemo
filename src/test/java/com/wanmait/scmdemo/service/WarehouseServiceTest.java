package com.wanmait.scmdemo.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WarehouseServiceTest {
    @Resource
    private WarehouseService warehouseService;

    //听课测试创建

    //查询所有
    @Test
    public void testList()
    {
        List<Warehouse> warehouseList = warehouseService.list();
        warehouseList.forEach(System.out::println);
    }
    //查询总条数
    @Test
    public void testCount()
    {
        long count = warehouseService.count();
        System.out.println(count);
    }
    //根据多个id查询
    @Test
    public void testBatchIds()
    {
        List<Warehouse> warehouseList = warehouseService.listByIds(Arrays.asList(1,2));
        warehouseList.forEach(System.out::println);
    }
    //测试使用Map查询
    @Test
    public void testListMap()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("warehouse_leader","张三");
        List<Warehouse> warehouseList = warehouseService.listByMap(map);
        warehouseList.forEach(System.out::println);
    }
    //测试分页
    @Test
    public void testPage()
    {
        Page<Warehouse> page = new Page<>(2,3);
        Page<Warehouse> warehousePage = warehouseService.page(page);
        System.out.println("总页数："+warehousePage.getPages());
        System.out.println("总条数"+warehousePage.getTotal());
        System.out.println("当前页："+warehousePage.getCurrent());
        System.out.println("每页显示条数："+warehousePage.getSize());
        List<Warehouse> warehouseList = warehousePage.getRecords();
        warehouseList.forEach(System.out::println);
    }
    //测试分页查询
    @Test
    public void testListPageByPageHelper()
    {
        PageInfo<Warehouse> pageInfo = warehouseService.list(1,3);
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("总条数"+pageInfo.getTotal());
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每页显示条数："+pageInfo.getPageSize());
        System.out.println("当前页实际条数："+pageInfo.getSize());
        System.out.println("上一页："+pageInfo.getPrePage());
        System.out.println("下一页："+pageInfo.getNextPage());
        int navigatepageNums[] = pageInfo.getNavigatepageNums();
        System.out.println(Arrays.toString(navigatepageNums));
        pageInfo.getList().forEach(System.out::println);
    }
    //测试自定义查询
    @Test
    public void findAll()
    {
        List<Warehouse> warehouseList = warehouseService.findAll();
        warehouseList.forEach(System.out::println);
    }
    //测试模糊搜索
    @Test
    public void testListByQueryWrapper()
    {
        warehouseService.listByQueryWrapper();
    }
    //Lambada形式模糊搜索
    @Test
    public void testListByLambdaQueryWrapper()
    {
        warehouseService.listByLambadaQueryWrapper();
    }
//=============================================================================================================



}
