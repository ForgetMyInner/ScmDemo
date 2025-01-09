package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.SupplierSign;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 供应商签约 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface SupplierSignService extends IService<SupplierSign> {
    SupplierSign getSupplierById(Integer supplierId);

}
