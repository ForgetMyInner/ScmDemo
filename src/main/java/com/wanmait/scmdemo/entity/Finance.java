package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 财务表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
public class Finance {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发票抬头
     */
    private String invoiceHeader;

    /**
     * 发票税号
     */
    private Integer taxNum;

    /**
     * 税种
     */
    private Integer taxType;

    /**
     * 增值税税率
     */
    private Integer taxRate;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 0 删除   
     */
    private Boolean enable;
}
