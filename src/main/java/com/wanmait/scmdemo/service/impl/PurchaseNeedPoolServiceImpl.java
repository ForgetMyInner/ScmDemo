package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.PurchaseNeedPool;
import com.wanmait.scmdemo.mapper.PurchaseNeedPoolMapper;
import com.wanmait.scmdemo.service.PurchaseNeedPoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购需求池展示（采购状态，付款状态，入库状态） 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseNeedPoolServiceImpl extends ServiceImpl<PurchaseNeedPoolMapper, PurchaseNeedPool> implements PurchaseNeedPoolService {

}
