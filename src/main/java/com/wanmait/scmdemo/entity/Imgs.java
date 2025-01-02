package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 附件图片表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
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
    private String imges;

//    // 新增非表属性，用于存储前端传来的数组数据
//    private List<String> imgList;
}
