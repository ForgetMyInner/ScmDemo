package com.wanmait.scmdemo.mapper;

import com.wanmait.scmdemo.entity.PurchaseApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 采购申请（还未购买） Mapper 接口
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface PurchaseApplyMapper extends BaseMapper<PurchaseApply> {
//检查是否唯一
    boolean exists(String purchaseApplyCode);
}
