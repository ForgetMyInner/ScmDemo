package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供应商签约
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("supplier_sign")
public class SupplierSign {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商_id
     */
    private Integer supplierId;

    /**
     * 签约开始日期
     */
    private Date startTime;

    /**
     * 签约结束日期
     */
    private Date endTime;

    /**
     * 结算期限
     */
    private String supplierSignDeadline;

    /**
     * 信用额度
     */
    private String creditLimit;

    /**
     * 采购负责人(根据部门选择)
     */
    private Integer purchasePerson;

    /**
     * 采购部门
     */
    private String purchaseDepartment;

    /**
     * ******************未定/可能用不到
     */
    private Integer imgsId;

    private Long enable;
}
