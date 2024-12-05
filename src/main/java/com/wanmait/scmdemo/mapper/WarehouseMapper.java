package com.wanmait.scmdemo.mapper;

import com.wanmait.scmdemo.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 仓库 Mapper 接口
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    List<Warehouse> findAll();
}
