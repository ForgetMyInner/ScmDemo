package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购明细表----申请（还未购买）
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("purchase_product_details")
public class PurchaseProductDetails {

    private Integer id;

    /**
     * 供应商产品信息表id
     */
    private Integer productSupplierId;

    /**
     * 采购需求数量
     */
    private Integer purchaseNum;

    /**
     * 是否寻求报价（0是寻求报价，1不寻求报价）
     */
    private Boolean quote;

    /**
     * 产品所在库存id
     */
    private Integer productWarehouseId;

    /**
     * 采购申请表的id
     */
    private Integer purchaseApplyId;

    /**
     * 总价格（单价*数量）
     */
    private BigDecimal totalPrice;

    /**
     * 实际采购数量
     */
    private String purchaseNumReal;

    /**
     * 审批是否通过根据申请表的审批来判断
     */
    private String approval;
}
