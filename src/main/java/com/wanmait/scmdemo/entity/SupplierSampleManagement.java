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
 * 供应商提交的的样品管理
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("supplier_sample_management")
public class SupplierSampleManagement {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商信息
     */
    private Integer supplierId;

    /**
     * 是否是新品0非新品
     */
    private Boolean isnew;

    /**
     * 邮寄时间
     */
    private Date mailingTime;

    /**
     * 邮寄单号
     */
    private String mailingCode;

    /**
     * 录入人
     */
    private String enterPerson;

    /**
     * 录入时间
     */
    private Date enterTime;

    private Boolean enable;
}
