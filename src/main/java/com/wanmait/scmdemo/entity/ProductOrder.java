package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品在订单里面的展示
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("product_order")
public class ProductOrder {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品编码（自动生成）
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

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
    private Integer priductUnit;

    /**
     * 产品采购权限
     */
    private Boolean purchasePermissions;

    /**
     * 产品图片
     */
    private Integer productImgsId;

    /**
     * 产品赠送权限
     */
    private Boolean presentedPermissions;

    /**
     * 采购单价
     */
    private BigDecimal productUnitPrice;

    /**
     * 产品销售权限
     */
    private Boolean salePermissions;

    /**
     * 样品数量(添加样品)
     */
    private Integer sampleProductNum;

    /**
     * 产品总价
     */
    private BigDecimal totalPrice;

    /**
     * 样品单价(添加样品)
     */
    private BigDecimal sampleProductPrice;

    /**
     * 历史报价
     */
    private BigDecimal historicalQuotes;

    /**
     * 采购数量需求(用于 采购申请)
     */
    private Integer purchaseNum;

    /**
     * 是否寻求报价（0不需要报价，1需要报价	
     */
    private Boolean ifneedQuote;

    /**
     * 假删除
     */
    private Boolean enable;

    /**
     * 录入人
     */
    private String enterPerson;

    /**
     * 录入时间
     */
    private Date enterTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updatePerson;
}
