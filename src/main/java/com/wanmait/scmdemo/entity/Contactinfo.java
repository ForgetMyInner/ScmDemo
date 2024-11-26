package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供应商的联系人信息表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
public class Contactinfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 联系人名称
     */
    private Integer name;

    /**
     * 职位
     */
    private Integer job;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 联系人邮箱
     */
    private String email;

    /**
     * 1表示重要联系人  0表示备用联系人
     */
    private Boolean isImportant;

    /**
     * 供应商id
     */
    private Integer supplierId;

    private Boolean enable;
}
