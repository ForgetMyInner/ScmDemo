package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.SupplierEnterRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.entity.SupplierEnterRuleDTO;
import com.wanmait.scmdemo.util.AjaxResult;

import java.util.List;

/**
 * <p>
 * 供应商准入条例 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-05
 */
public interface SupplierEnterRuleService extends IService<SupplierEnterRule> {

    /*插入准入条例对象 包含： 一个条例名称  多个规则*/
    AjaxResult insertDTO(SupplierEnterRuleDTO supplierEnterRuleDTO);

    /*查询多条准入条例*/
    AjaxResult findAll();

    /*批量删除*/
    AjaxResult deleteBatch(List<Integer> ids);
}
