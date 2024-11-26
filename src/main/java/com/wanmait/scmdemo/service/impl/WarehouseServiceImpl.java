package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.mapper.WarehouseMapper;
import com.wanmait.scmdemo.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
