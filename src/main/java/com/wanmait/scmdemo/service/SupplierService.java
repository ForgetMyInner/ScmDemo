package com.wanmait.scmdemo.service;

import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.Attachment;
import com.wanmait.scmdemo.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.entity.SupplierDTO;
import com.wanmait.scmdemo.util.AjaxResult;

/**
 * <p>
 * 供应商信息表 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
public interface SupplierService extends IService<Supplier> {
    AjaxResult register(SupplierDTO dto);
    AjaxResult findBySupplierState();
    PageInfo<Supplier> supplierWithAttachAndContact(Supplier supplier,int pageNum, int pageSize);

}
