package com.wanmait.scmdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WarehouseAssistantDTO {
    private Integer id;
    private Warehouse warehouse;
    private ProductShow productShow;
    private ProductWarehouse productWarehouse;
}
