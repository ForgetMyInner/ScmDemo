package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.PayPlan;
import com.wanmait.scmdemo.mapper.PayPlanMapper;
import com.wanmait.scmdemo.service.PayPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购订单付款计划表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayPlanServiceImpl extends ServiceImpl<PayPlanMapper, PayPlan> implements PayPlanService {

}
