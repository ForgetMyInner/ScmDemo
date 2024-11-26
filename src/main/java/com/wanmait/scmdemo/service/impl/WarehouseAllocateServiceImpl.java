package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.WarehouseAllocate;
import com.wanmait.scmdemo.mapper.WarehouseAllocateMapper;
import com.wanmait.scmdemo.service.WarehouseAllocateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存调拨表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WarehouseAllocateServiceImpl extends ServiceImpl<WarehouseAllocateMapper, WarehouseAllocate> implements WarehouseAllocateService {

}
