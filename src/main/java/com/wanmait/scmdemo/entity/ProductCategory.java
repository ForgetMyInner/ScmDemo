package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("product_category")
public class ProductCategory {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品类型名称
     */
    private Integer productType;

    /**
     * 提交人
     */
    private Integer enterPerson;

    /**
     * 提交时间
     */
    private Date enterTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 产品属性
     */
    private Integer productAttribute;

    private Boolean enable;
}
