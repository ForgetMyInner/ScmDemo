package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * <p>
 * 供应商信息表
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
@Data
@Accessors(chain = true)
@Document(indexName = "supplier")
@Setting(shards = 5,replicas = 1)
public class Supplier {

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @Field(type = FieldType.Keyword)
    private Integer id;

    /**
     * 供应商编码 自动生成
     */
    @Field(name = "supplier_code",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierCode;

    /**
     * 供应商准入编号
     */
    @Field(name = "supplier_num",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierNum;

    /**
     * 供应商名称
     */
    @TableField(condition = "%s LIKE CONCAT('%%',#{%s},'%%')")
    @Field(name = "supplier_name",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierName;

    /**
     * 供应商分类（数据库字典）
     */
    @Field(name = "supplier_type",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierType;

    /**
     * 供应商状态(注册时)/评级(主管评分后)
     */
    @Field(name = "supplier_State",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierState;

    /**
     * 供应商地址（三级联动+详细地址）
     */
    @Field(name = "supplier_address",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierAddress;

    /**
     * 发票抬头
     */
    @Field(name = "invoice_header",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String invoiceHeader;

    /**
     * 发票税号
     */
    @Field(name = "tax_num",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String taxNum;

    /**
     * 税种
     */
    @Field(name = "tax_type",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String taxType;

    /**
     * 税率（13% 9%）
     */
    @Field(name = "tax_rate",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String taxRate;

    /**
     * 开户银行（四大行）
     */
    @Field(name = "bank",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String bank;

    /**
     * 银行账号
     */
    @Field(name = "bank_num",type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
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
    @Field(name = "enter_time",type = FieldType.Date,format = {},pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date enterTime;

    /**
     * 录入人
     */
    @Field(name = "enter_person", type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String enterPerson;

    /**
     * 修改时间
     */
    @Field(name = "update_time",type = FieldType.Date,format = {},pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updateTime;

    /**
     * 修改人
     */
    @Field(name = "update_person", type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
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

    private Integer cityId;
    @Field(name = "suppplier_level", type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String supplierLevel;

    @TableField(exist = false)
    private Attachment attachment;
    @TableField(exist = false)
    private Contactinfo contactinfo;
    @TableField(exist = false)
    private SupplierSign supplierSign;

}
