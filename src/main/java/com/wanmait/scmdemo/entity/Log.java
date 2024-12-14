package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanmait
 * @since 2024-12-14
 */
@Getter
@Setter
@Accessors(chain = true)
public class Log {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer adminId;

    private Date inputTime;

    private String ip;

    private String info;
}
