package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件图片表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Getter
@Setter
@Accessors(chain = true)
public class Imgs {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 谁用到的id
     */
    private Integer pid;

    /**
     * 类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    private Integer sort;

    private Boolean enable;
}
