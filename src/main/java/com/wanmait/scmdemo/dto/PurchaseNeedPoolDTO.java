package com.wanmait.scmdemo.dto;

import com.wanmait.scmdemo.entity.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

@Data
@Accessors(chain = true)
public class PurchaseNeedPoolDTO {

    @Transient


    private Integer id;
    //产品信息的id
    private ProductShow productShow;
    //仓库信息
    private Warehouse warehouse;
    //供应商信息
    private Supplier supplier;
    //产品所在库存信息
    private ProductWarehouse productWarehouse;
    //产品供应商的信息
    private ProductSupplier productSupplier;
    //产品申请表的id
    private PurchaseApply purchaseApply;
    //员工信息表
    private Employee employee;
    //采购明细表
    private PurchaseProductDetails purchaseProductDetails;
    //部门表
    private CompanyDepartment companyDepartment;

}
