package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供应商送货表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("supplier_deliver_goods")
public class SupplierDeliverGoods {

    private Integer id;

    /**
     * 采购订单id
     */
    private Integer purchaseOrderId;

    /**
     * 送货联系人id
     */
    private Integer deliverPersonId;

    /**
     * 送货单编号
     */
    private String deliverCode;

    /**
     * 供应商送货时间
     */
    private Date deliverDate;

    /**
     * 预计到货时间
     */
    private Date planArrivalDate;

    /**
     * 实际送达时间
     */
    private Date arrivalDate;

    /**
     * 图片
     */
    private Integer arrivalImgsId;

    /**
     * 送货确认
     */
    private Boolean sure;
}
