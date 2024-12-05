package com.wanmait.scmdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class Warehouse {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库编码(自动生成)
     */
    private String code;

    /**
     * 仓库地址（三级联动+详细地址）
     */
    private String address;

    /**
     * 仓库容量
     */
    private String capacity;

    /**
     * 仓库性质（数据字典）
     */
    private String nature;

    /**
     * 公司部门id
     */
    private String companyDepartmentId;

    /**
     * 仓库负责人
     */
    private String warehouseLeader;

    /**
     * 仓库负责人电话
     */
    private String phone;

    /**
     * 最低库存预警
     */
    private Integer minCount;

    /**
     * 最高库存预警
     */
    private Integer maxCount;

    /**
     * 入库产品信息id
     */
    private Integer productManagementId;

    /**
     * 入库数量
     */
    private Integer instoreNum;

    /**
     * 当前库存数量
     */
    private Integer nowCount;

    /**
     * 租金
     */
    private BigDecimal rent;

    /**
     * 到期时间
     */
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
