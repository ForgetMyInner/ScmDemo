package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品在库存
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("product_warehouse")
public class ProductWarehouse {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品展示id
     */
    private Integer productShow;

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

    /**
     * 库存数量
     */
    private Integer productNum;

    /**
     * 仓库id
     */
    private Integer warehouseId;
}
