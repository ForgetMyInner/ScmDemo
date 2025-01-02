package com.wanmait.scmdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.mapper.ProductShowMapper;
import com.wanmait.scmdemo.mapper.ProductWarehouseMapper;
import com.wanmait.scmdemo.mapper.WarehouseMapper;
import com.wanmait.scmdemo.service.ProductWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品在库存 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductWarehouseServiceImpl extends ServiceImpl<ProductWarehouseMapper, ProductWarehouse> implements ProductWarehouseService {

    @Resource
    private ProductShowMapper productShowMapper;
    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public PageInfo<ProductWarehouse> assistantPageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductWarehouse> productWarehouses = this.list();
        productWarehouses.forEach(productWarehouse -> {
            //仓库表中没有产品信息id   所以从产品库存表中取出产品展示id 产品id是唯一的   仓库id不唯一  可能多个商品对应一个仓库
            int productShowId = productWarehouse.getProductShow();
            ProductShow productShow = productShowMapper.selectById(productShowId);
            productWarehouse.setProductShowEntity(productShow);
            //查询产品所在的仓库
            //仓库id
            int warehouseId = productWarehouse.getWarehouseId();
            Warehouse warehouse = warehouseMapper.selectById(warehouseId);
            productWarehouse.setWarehouseEntity(warehouse);
        });
        return new PageInfo<>(productWarehouses, 11);
    }
}
