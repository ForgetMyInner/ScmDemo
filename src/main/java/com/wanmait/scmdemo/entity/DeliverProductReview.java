package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购入库审核表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("deliver_product_review")
public class DeliverProductReview {

    /**
     * 送货明细表id
     */
    private Integer deliverProductDetailsId;

    /**
     * 入库确认
     */
    private Boolean qualifiedEnsure;

    /**
     * 入库图片
     */
    private Integer imgsId;

    /**
     * 入库时间
     */
    private Integer inwarehouseTime;

    /**
     * 入库人
     */
    private Integer inwarehousePerson;

    /**
     * 所属部门
     */
    private Integer personDep;
}
