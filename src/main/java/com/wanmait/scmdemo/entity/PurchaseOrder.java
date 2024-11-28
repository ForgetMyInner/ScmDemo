package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购订单
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("purchase_order")
public class PurchaseOrder {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 采购订单名称
     */
    private String purchaseOrderName;

    /**
     * 订单签订日期
     */
    private Date purchaseSignDate;

    /**
     * 订单交付日期
     */
    private Date purchaseDeliveryDate;

    /**
     * 采购订单编号（自动生成）
     */
    private String purchaseCode;

    /**
     * 部门的id
     */
    private Integer departmentId;

    /**
     * 员工的id
     */
    private Integer employeeId;

    /**
     * 入库的仓库id
     */
    private Integer warehouseId;

    /**
     * 合同附件id
     */
    private Integer purchaseOrderImgId;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 订单申请表
     */
    private Integer purchaseApplyId;

    /**
     * 审批结果（默认前台不显示，审批时候显示默认为0,1为同意
     */
    private String approvalStatus;

    /**
     * 审批时间
     */
    private Date approvalTime;

    /**
     * 审批人id
     */
    private Integer approvalPersonId;

    /**
     * 订单总金额
     */
    private BigDecimal totalPrice;
}
