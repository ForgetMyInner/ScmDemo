package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购申请到货计划表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("arrival_plan")
public class ArrivalPlan {

    private Integer id;

    /**
     * 到货批次(数据库字典—）
     */
    private String purchaseOrderArrival;

    /**
     * 计划到货日期
     */
    private Date planArrivalDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 采购订单id
     */
    private Integer purchaseOrderId;
}
