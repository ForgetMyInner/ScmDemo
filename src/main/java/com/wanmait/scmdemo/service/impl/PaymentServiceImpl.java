package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Payment;
import com.wanmait.scmdemo.mapper.PaymentMapper;
import com.wanmait.scmdemo.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购订单的付款单 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
