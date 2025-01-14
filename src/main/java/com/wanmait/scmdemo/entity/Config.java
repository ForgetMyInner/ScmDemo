package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanmait
 * @since 2025-01-07
 */
@Getter
@Setter
@Accessors(chain = true)
public class Config {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String configKey;

    private String configValue;

    private String defaultValue;

    private String description;
}
