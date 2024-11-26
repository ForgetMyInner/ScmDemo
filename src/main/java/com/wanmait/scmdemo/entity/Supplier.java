package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供应商信息表
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
public class Supplier {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商编码 自动生成
     */
    private String supplierCode;

    /**
     * 供应商准入编号
     */
    private String supplierNum;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商分类（数据库字典）
     */
    private String supplierType;

    /**
     * 供应商状态(注册时)/评级(主管评分后)
     */
    private String supplierState;

    /**
     * 供应商地址（三级联动+详细地址）
     */
    private String supplierAddress;

    /**
     * 发票抬头
     */
    private String invoiceHeader;

    /**
     * 发票税号
     */
    private String taxNum;

    /**
     * 税种
     */
    private String taxType;

    /**
     * 税率（13% 9%）
     */
    private String taxRate;

    /**
     * 开户银行（四大行）
     */
    private String bank;

    /**
     * 银行账号
     */
    private String bankNum;

    /**
     * 联系人_id
     */
    private Integer contactinfoId;

    /**
     * 附件_id
     */
    private Integer attachmentId;

    /**
     * 录入时间
     */
    private Date enterTime;

    /**
     * 录入人
     */
    private String enterPerson;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updatePerson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 禁用：1 可用
     */
    private Boolean stop;

    /**
     * 删除：1可用
     */
    private Boolean enable;
}
