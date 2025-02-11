package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * <p>
 * 仓库
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("warehouse")
public class Warehouse {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库名称
     */
    @Field(name = "name", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String name;

    /**
     * 仓库编码(自动生成)
     */
    @Field(name = "code", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String code;

    /**
     * 仓库地址（三级联动+详细地址）
     */
    @Field(name = "address", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String address;

    /**
     * 仓库容量
     */
    @Field(name = "capacity", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String capacity;

    /**
     * 仓库性质（数据字典）
     */
    @Field(name = "nature", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String nature;

    /**
     * 公司部门id
     */
    @Field(name = "company_department_id", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String companyDepartmentId;

    /**
     * 仓库负责人
     */
    @Field(name = "warehouse_leader", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String warehouseLeader;

    /**
     * 仓库负责人电话
     */
    @Field(name = "phone", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String phone;

    /**
     * 最低库存预警
     */
    @Field(name = "min_count", type = FieldType.Integer)
    private Integer minCount;

    /**
     * 最高库存预警
     */
    @Field(name = "max_count", type = FieldType.Integer)
    private Integer maxCount;

    /**
     * 入库产品信息id
     */
    @Field(name = "product_management_id", type = FieldType.Integer)
    private Integer productManagementId;

    /**
     * 入库数量
     */
    @Field(name = "instore_num", type = FieldType.Integer)
    private Integer instoreNum;

    /**
     * 当前库存数量
     */
    @Field(name = "now_count", type = FieldType.Integer)
    private Integer nowCount;

    /**
     * 租金
     */
    private BigDecimal rent;

    /**
     * 到期时间
     */
    @Field(name = "end_time", type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endTime;

    /**
     * 仓库状态：是否可用    1可用
     */

//    //默认的value为0表示可用,1为删除,需手动设置
//    @TableLogic(value = "1",delval = "0")
//    private Boolean stop;
    private Boolean stop;
    //
//    //默认的value为0表示可用,1为删除,需手动设置
//    @TableLogic(value = "1",delval = "0")
//    private Boolean enable;
    private Boolean enable;
}
