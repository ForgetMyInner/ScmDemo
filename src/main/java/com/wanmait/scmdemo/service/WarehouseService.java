package com.wanmait.scmdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.Warehouse;

import java.util.List;

/**
 * <p>
 * 仓库 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface WarehouseService extends IService<Warehouse> {
    //听课测试创建
    PageInfo<Warehouse> list(int pageNum,int pageSize);
    //自定义添加方法
    List<Warehouse> findAll();
    //模糊查询
    void listByQueryWrapper();
    //Lambada形式
    void listByLambadaQueryWrapper();
    //------------------------------------------------------------------------------------------------------
    //实现代码

    //添加仓库


}
