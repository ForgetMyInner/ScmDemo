package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购订单付款计划表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("pay_plan")
public class PayPlan {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 付款项（在数据字典）
     */
    private String purchaseOrderPay;

    /**
     * 付款比例
     */
    private String purchaseProprtion;

    /**
     * 付款方式（在数据字典）
     */
    private String purchaseMethod;

    /**
     * 计划付款日期
     */
    private Date purchaseDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 采购订单id
     */
    private Integer purchaseOrderId;
}
