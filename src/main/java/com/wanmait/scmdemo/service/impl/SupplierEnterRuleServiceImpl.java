package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.SupplierEnterRule;
import com.wanmait.scmdemo.mapper.SupplierEnterRuleMapper;
import com.wanmait.scmdemo.service.SupplierEnterRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 供应商准入条例 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierEnterRuleServiceImpl extends ServiceImpl<SupplierEnterRuleMapper, SupplierEnterRule> implements SupplierEnterRuleService {

}
