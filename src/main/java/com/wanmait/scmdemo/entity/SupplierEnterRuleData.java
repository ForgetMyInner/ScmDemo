package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准则条例中的>>>多个评估项
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("supplier_enter_rule_data")
public class SupplierEnterRuleData {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer enterRuleId;

    /**
     * 多个评估项
     */
    private String rules;

    /**
     * 最高分（自己写的）
     */
    private String maxScore;

    /**
     * 总分（根据每一项得分算出来）
     */
    private String totalScore;

    /**
     * 咱们给打分（评分）
     */
    private String score;

    /**
     * 备注
     */
    private String remark;

    /**
     * 1 可用
     */
    private Boolean enable;
}
