package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.OtherInWarehouse;
import com.wanmait.scmdemo.mapper.OtherInWarehouseMapper;
import com.wanmait.scmdemo.service.OtherInWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 其他入库单 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OtherInWarehouseServiceImpl extends ServiceImpl<OtherInWarehouseMapper, OtherInWarehouse> implements OtherInWarehouseService {

}
