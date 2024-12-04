package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 城市
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
@Data
@Accessors(chain = true)
public class City {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sort;

    private String name;

    private Integer provinceId;
}
