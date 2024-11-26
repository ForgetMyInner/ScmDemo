package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购需求池展示（采购状态，付款状态，入库状态）
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("purchase_need_pool")
public class PurchaseNeedPool {

    private Integer id;

    /**
     * 采购状态id
     */
    private Integer purchaseProductDetailsId;
}
