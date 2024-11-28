package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存盘点
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("inventory_counts")
public class InventoryCounts {

    private Integer id;

    /**
     * 盘点类型
     */
    private String inventoryType;

    /**
     * 盘点单编号
     */
    private String inventoryCode;

    /**
     * 盘点开始日期
     */
    private Date inventoryDate;

    /**
     * 盘点结束日期
     */
    private Date inventoryEndDate;

    /**
     * 盘点员
     */
    private String inventoryPerson;

    /**
     * 盘点员归属部门
     */
    private String inventoryDepartment;

    /**
     * 盘点仓库
     */
    private Integer inventoryWarehouseId;

    /**
     * 盘点产品明细
     */
    private Integer productManagementId;

    /**
     * 盘亏总数
     */
    private String inventoryLoss;

    /**
     * 盘盈总数
     */
    private String inventoryWin;
}
