package com.wanmait.scmdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SupplierDTO {
    private Integer id;
    private Supplier supplier;
    private Contactinfo contactinfo;
    private Attachment attachment;
}
