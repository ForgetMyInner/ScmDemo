package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.mapper.WarehouseMapper;
import com.wanmait.scmdemo.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> getWarehouseList(){
        return this.list();
    }

    //听课测试创建
    @Override
    public PageInfo<Warehouse> list(int pageNum, int pageSize) {
        //只针对后边最接近的查询语句起效(就近原则),后边可能有多个查询
        PageHelper.startPage(pageNum,pageSize);
        List<Warehouse> warehouseList = warehouseMapper.selectList(null);

        return new PageInfo<>(warehouseList);
    }
    //自定义创建查询所有
    @Override
    public List<Warehouse> findAll() {
        return warehouseMapper.findAll();
    }
    //模糊查询
    @Override
    public void listByQueryWrapper() {
        //指定查询条件或者排序都使用表的列名而不是类的属性名
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<>();
        //参数自动变成%三%
        queryWrapper.like("warehouse_leader","三");
        //指定排序(有 排序列名 "sort" 参数改成 sort)
        queryWrapper.orderByAsc("id");

        List<Warehouse> warehouseList = warehouseMapper.selectList(queryWrapper);
        warehouseList.forEach(System.out::println);
    }
    //Lambda形式查询
    @Override
    public void listByLambadaQueryWrapper() {
        LambdaQueryWrapper<Warehouse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Warehouse::getWarehouseLeader,"三");
        queryWrapper.orderByAsc(Warehouse::getId);
        List<Warehouse> warehouseList = warehouseMapper.selectList(queryWrapper);
        warehouseList.forEach(System.out::println);
    }
    //-----------------------------------------------------------------------------------------------------------------

}
