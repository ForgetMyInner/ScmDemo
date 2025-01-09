package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.util.filetypedetector.FileType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * <p>
 * 产品在库存
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Data
@Accessors(chain = true)
@TableName("product_warehouse")
@Document(indexName = "productwarehouse")
@Setting(shards = 5, replicas = 1)//5个分片，1个副本
public class ProductWarehouse {

    @TableId(value = "id", type = IdType.AUTO)
    /*
     * lzq:该注解用于ES，加了此注解，该字段作为文档的id
     *   类型自动为FiledType。KeyWord类型，就是字符串类型
     * */

    @Id
    private Integer id;

    /**
     * 产品展示id
     */
    @Field(name = "product_show",type = FieldType.Integer)
    private Integer productShow;
    @TableField(exist = false)
    private ProductShow productShowEntity;//产品对象
    /**
     * 假删除
     */
    @Field(type = FieldType.Boolean)
    private Boolean enable;

    /**
     * 录入人
     */
    @Field(name = "enter_person", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String enterPerson;

    /**
     * 录入时间
     */
    @Field(name = "enter_time", type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date enterTime;

    /**
     * 更新时间
     */
    @Field(name = "update_time", type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updateTime;

    /**
     * 更新人
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String updatePerson;

    /**
     * 库存数量
     */
    private Integer productNum;

    /**
     * 仓库id
     */
    @Field(name = "warehouse_id",type = FieldType.Integer)
    private Integer warehouseId;

    @TableField(exist = false)
    private Warehouse warehouseEntity;//仓库对象
}
