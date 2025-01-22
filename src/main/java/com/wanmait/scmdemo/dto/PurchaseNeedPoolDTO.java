package com.wanmait.scmdemo.dto;

import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.entity.ProductSupplier;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.entity.PurchaseApply;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PurchaseNeedPoolDTO {
    private Integer id;
    //产品信息的id
    private ProductShow productShow;
    //产品所在库存信息
    private ProductWarehouse productWarehouse;
    //产品供应商的信息
    private ProductSupplier productSupplier;
    //产品申请表的id
    private PurchaseApply purchaseApply;
}
