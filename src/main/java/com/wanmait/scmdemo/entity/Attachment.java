package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
public class Attachment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 证书类型（数据库字典）
     */
    private String type;

    /**
     * 供应商_id
     */
    private Integer supplierId;

    /**
     * （0删除 ）
     */
    private Boolean enable;
}
