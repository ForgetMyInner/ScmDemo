package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
public class Employee {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工名称
     */
    private String name;

    /**
     * 员工手机号
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 职位
     */
    private String job;

    /**
     * 所属部门
     */
    private Integer companyDepartmentId;

    /**
     * 逻辑删除 0表示删除
     */
    private Boolean enable;
}
