package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Finance;
import com.wanmait.scmdemo.mapper.FinanceMapper;
import com.wanmait.scmdemo.service.FinanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 财务表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {

}
