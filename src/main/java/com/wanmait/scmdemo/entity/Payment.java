package com.wanmait.scmdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购订单的付款单
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
public class Payment {

    private Integer id;

    /**
     * 采购订单的id
     */
    private Integer purchaseOrderId;
}
