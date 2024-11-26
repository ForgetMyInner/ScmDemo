package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.InventoryCounts;
import com.wanmait.scmdemo.mapper.InventoryCountsMapper;
import com.wanmait.scmdemo.service.InventoryCountsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存盘点 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InventoryCountsServiceImpl extends ServiceImpl<InventoryCountsMapper, InventoryCounts> implements InventoryCountsService {

}
