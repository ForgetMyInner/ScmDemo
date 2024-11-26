package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 其他入库单
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("other_in_warehouse")
public class OtherInWarehouse {

    private Integer id;

    /**
     * 入库类型
     */
    private String otherInWarehouse;

    /**
     * 入库仓库id
     */
    private Integer warehouserId;

    /**
     * 其他入库单编号（自动生成）
     */
    private String otherInWarehouseCode;

    /**
     * 入库产品明细
     */
    private Integer productManagementId;

    /**
     * 入库产品总数
     */
    private Integer otherInWarehouseNum;

    /**
     * 入库产品总额
     */
    private BigDecimal ohterInWarehouseMoney;

    /**
     * 入库确认
     */
    private Boolean inWarehouseConfirm;

    /**
     * 入库确认图片（附件）
     */
    private Integer otherWarehouseImgsId;

    /**
     * 入库时间
     */
    private Date otherWarehouseDate;

    /**
     * 入库人
     */
    private Integer inWarehousePerson;

    /**
     * 归属部门
     */
    private Integer personDepartment;
}
