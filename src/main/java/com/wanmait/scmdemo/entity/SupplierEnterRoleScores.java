package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供应商准入得分
 * </p>
 *
 * @author wanmait
 * @since 2024-12-25
 */
@Data
@Accessors(chain = true)
@TableName("supplier_enter_role_scores")
public class SupplierEnterRoleScores {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商的id
     */
    private Integer supplierId;

    /**
     * 条例名称
     */
    private String ruleName;

    /**
     * 准则名称
     */
    private String ruleDataName;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 说明
     */
    private String remark;

    private Date creatTime;

    private String creatPerson;
}
