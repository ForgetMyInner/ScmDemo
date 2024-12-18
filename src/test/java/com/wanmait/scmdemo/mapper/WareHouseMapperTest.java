package com.wanmait.scmdemo.mapper;

import com.wanmait.scmdemo.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class WareHouseMapperTest {
    @Resource
    private WarehouseMapper warehouseMapper;
    //添加
    @Test
    public void testInsert()
    {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(2);
        warehouse.setCapacity("2");
        warehouse.setWarehouseLeader("李四");
        warehouse.setPhone("15554439999");
        warehouseMapper.insert(warehouse);
    }
    //根据id查找
    @Test
    public void testSelectById()
    {
        Warehouse warehouse = warehouseMapper.selectById(2);
        System.out.println(warehouse);
    }
    //查看全部
    @Test
    public void testSelectList()
    {
        List<Warehouse> warehouseList = warehouseMapper.selectList(null);
        warehouseList.forEach(System.out::println);
    }
    //删除
    @Test
    public void testDeleteById()
    {
        warehouseMapper.deleteById(1);
    }
    //修改
    @Test
    public void testUptateById()
    {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1).setWarehouseLeader("王五");
        warehouseMapper.updateById(warehouse);
    }

}
