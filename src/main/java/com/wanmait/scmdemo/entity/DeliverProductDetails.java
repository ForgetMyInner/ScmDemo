package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 送货产品明细表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("deliver_product_details")
public class DeliverProductDetails {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 采购申请id获得采购产品明细下的产品信息
     */
    private Integer purchaseApplyId;

    /**
     * 送货适量
     */
    private Integer deliverGoodsNum;

    /**
     * 是否送达
     */
    private Boolean isDelivered;

    /**
     * 附件图片id
     */
    private Integer deliveredImgs;

    /**
     * 入库验证
     */
    private Boolean inWarehouseSure;

    /**
     * 本次到货数量
     */
    private Integer realProductNum;

    /**
     * 不合格数量
     */
    private Integer notEligible;

    /**
     * 本次入库数量
     */
    private Integer inWarehouseNum;

    /**
     * 是否存在不合格产品
     */
    private Boolean haveEligible;

    /**
     * 质检时间
     */
    private Date qualityTime;

    /**
     * 当前质检员
     */
    private Integer qualityPerson;

    /**
     * 部门
     */
    private Integer qualityPersonDep;
}
