package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 其他出库单
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("other_out_warehouse")
public class OtherOutWarehouse {

    private Integer id;

    /**
     * 出库类型
     */
    private String otherOutWarehouse;

    /**
     * 出库仓库id
     */
    private Integer warehouserId;

    /**
     * 其他出库单编号（自动生成）
     */
    private String otherOutWarehouseCode;

    /**
     * 出库产品明细
     */
    private Integer productManagementId;

    /**
     * 出库产品总数
     */
    private Integer otherOutWarehouseNum;

    /**
     * 出库产品总额
     */
    private BigDecimal ohterOutWarehouseMoney;

    /**
     * 出库确认
     */
    private Boolean outWarehouseConfirm;

    /**
     * 出库确认图片（附件）
     */
    private Integer otherWarehouseImgsId;

    /**
     * 出库时间
     */
    private Date otherWarehouseDate;

    /**
     * 出库人
     */
    private Integer outWarehousePerson;

    /**
     * 归属部门
     */
    private Integer personDepartment;
}
