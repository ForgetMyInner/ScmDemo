package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 进项发票
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("input_invoice")
public class InputInvoice {

    private Integer id;

    /**
     * 采购订单id
     */
    private Integer purchaseOrderId;

    /**
     * 发票附件上传
     */
    private String invoiceImage;

    /**
     * 本次付款金额(若小于订单金额则提交完毕之后与订单金额相减）
     */
    private BigDecimal paymentThisTime;

    /**
     * 发票的类型(数据库字典）
     */
    private String invoiceType;

    /**
     * 发票号码
     */
    private String incoiceNum;

    /**
     * 收票时间
     */
    private Date incoiceTime;
}
