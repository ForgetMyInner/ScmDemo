package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品-----供应商关系表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("product_supplier")
public class ProductSupplier {

    private Integer id;

    /**
     * 产品展示id
     */
    private Integer productShowId;
    @TableField(exist = false)
    private ProductShow productShow;

    /**
     * 供应商id
     */
    private Integer supplierId;
    @TableField(exist = false)
    private Supplier supplier;

    /**
     * 标准采购单价（含税）
     */
    private BigDecimal productUnitPrice;

    /**
     * 采购单价（含税）
     */
    private BigDecimal productPrice;

    /**
     * 折扣率
     */
    private String discountRate;

    /**
     * 增值税率
     */
    private String tax;
}
