package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存调拨表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("warehouse_allocate")
public class WarehouseAllocate {

    private Integer id;

    /**
     * 调拨类型(数据库字典）
     */
    private String allocateType;

    /**
     * 调拨申请日期
     */
    private Date allocateDate;

    /**
     * 期望到货日期
     */
    private Date allocateExpectation;

    /**
     * 申请人
     */
    private Integer applyPersonId;

    /**
     * 申请部门
     */
    private String applyDepartment;

    /**
     * 调拨单编号(自动生成）
     */
    private String allocateCode;

    /**
     * 调出仓库id
     */
    private Integer outWarehouse;

    /**
     * 调入仓库id
     */
    private Integer inWarehouse;

    /**
     * 调拨产品总额
     */
    private BigDecimal allocateProductMoney;

    /**
     * 调拨产品总数
     */
    private Integer allocateProductNum;

    /**
     * 调拨产品明细id
     */
    private Integer productManagementId;
}
