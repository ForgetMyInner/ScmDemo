package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 送货联系人
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("deliver_person")
public class DeliverPerson {

    private Integer id;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 发货人姓名
     */
    private String shipperName;

    /**
     * 发货人联系方式
     */
    private String shipperPhone;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机联系方式
     */
    private String driverPhone;

    /**
     * 车牌号
     */
    private String carNum;
}
