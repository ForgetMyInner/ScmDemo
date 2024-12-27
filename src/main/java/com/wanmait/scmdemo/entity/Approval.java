package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 审批表
 * </p>
 *
 * @author wanmait
 * @since 2024-12-27
 */
@Getter
@Setter
@Accessors(chain = true)
public class Approval {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商准入/供应商签约/报价单/销售订单(1/2/3/4)
     */
    private Integer symbol;

    /**
     * 供应商准入/供应商签约/报价单/销售订单id
     */
    private Integer aimId;

    /**
     * 审批时间
     */
    private Date time;

    /**
     * 审批人
     */
    private Integer userinfoId;

    /**
     * 审批状态（0代表未审批
     */
    private Boolean status;

    /**
     * 审批不通过原因
     */
    private String reason;
}
