package com.wanmait.scmdemo.dto;

import com.wanmait.scmdemo.entity.PurchaseApply;
import com.wanmait.scmdemo.entity.PurchaseProductDetails;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class PurchaseApplyDTO {
    private PurchaseApply purchaseApply;
    private List<PurchaseProductDetails> purchaseProductDetails;

    // Getters and Setters
    public PurchaseApply getPurchaseApply() {
        return purchaseApply;
    }

    public void setPurchaseApply(PurchaseApply purchaseApply) {
        this.purchaseApply = purchaseApply;
    }

    public List<PurchaseProductDetails> getPurchaseProductDetails() {
        return purchaseProductDetails;
    }

    public void setPurchaseProductDetails(List<PurchaseProductDetails> purchaseProductDetails) {
        this.purchaseProductDetails = purchaseProductDetails;
    }
}
