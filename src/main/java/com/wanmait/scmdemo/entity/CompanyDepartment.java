package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司部门表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("company_department")
public class CompanyDepartment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 领导人
     */
    private String leader;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 录入人
     */
    private String enterPerson;

    private Date enterTime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否禁用   0表示禁用
     */
    private Boolean stop;

    /**
     * 是否删除  0表示删除
     */
    private Boolean enable;
}
