package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.ArrivalPlan;
import com.wanmait.scmdemo.mapper.ArrivalPlanMapper;
import com.wanmait.scmdemo.service.ArrivalPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 采购申请到货计划表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArrivalPlanServiceImpl extends ServiceImpl<ArrivalPlanMapper, ArrivalPlan> implements ArrivalPlanService {

}
