package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.InputInvoice;
import com.wanmait.scmdemo.mapper.InputInvoiceMapper;
import com.wanmait.scmdemo.service.InputInvoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 进项发票 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InputInvoiceServiceImpl extends ServiceImpl<InputInvoiceMapper, InputInvoice> implements InputInvoiceService {

}
