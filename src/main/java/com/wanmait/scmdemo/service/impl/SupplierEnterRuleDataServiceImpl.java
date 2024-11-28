package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.SupplierEnterRuleData;
import com.wanmait.scmdemo.mapper.SupplierEnterRuleDataMapper;
import com.wanmait.scmdemo.service.SupplierEnterRuleDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 准则条例中的>>>多个评估项 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierEnterRuleDataServiceImpl extends ServiceImpl<SupplierEnterRuleDataMapper, SupplierEnterRuleData> implements SupplierEnterRuleDataService {

}
