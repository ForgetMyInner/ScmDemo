package com.wanmait.scmdemo.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SupplierEnterRuleDTO {

    private Integer id;
    private SupplierEnterRule supplierEnterRule;
    private List<SupplierEnterRuleData> supplierEnterRuleDataList;
}

