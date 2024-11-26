package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资质信息表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
public class Qualificationinfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 证书类别
     */
    private String type;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 附件id
     */
    private Integer attachmentId;

    private Boolean enable;
}
