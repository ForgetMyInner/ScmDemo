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
 * 采购申请（还未购买）
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("purchase_apply")
public class PurchaseApply {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 采购申请编号
     */
    private String code;

    /**
     * 申请人id
     */
    private Integer employeeId;

    /**
     * 申请时间
     */
    private Date enterTime;

    /**
     * 到货需求日期
     */
    private Date deliveryDemandTime;

    /**
     * 入库仓库
     */
    private Integer warehouseId;

    /**
     * 需求来源
     */
    private String source;

    /**
     * 备注
     */
    private String remark;

    /**
     * 采购价格总额
     */
    private BigDecimal allPrice;

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
     * 是否需要询价（1需要，0不需要）
     */
    private Integer rfqQuote;
}
