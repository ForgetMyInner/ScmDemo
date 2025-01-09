package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 样品表
 * </p>
 *
 * @author wanmait
 * @since 2024-12-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sample_table")
public class SampleTable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 样品管理表id
     */
    private Integer supplierSampleManagementId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编码（自动生成）
     */
    private String productCode;

    /**
     * 产品品牌
     */
    private String productBrand;

    /**
     * 产品属性
     */
    private String productAttribute;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品规格型号
     */
    private String productModels;

    /**
     * 产品单位
     */
    private String priductUnit;

    /**
     * 产品图片
     */
    private Integer productImgsId;

    /**
     * 样品数量(添加样品)
     */
    private Integer sampleProductNum;

    /**
     * 样品单价(添加样品）
     */
    private BigDecimal productUnitPrice;;

    /**
     * 产品总价
     */
    private BigDecimal totalPrice;
}
