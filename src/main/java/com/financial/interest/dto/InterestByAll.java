package com.financial.interest.dto;

import com.financial.interest.entity.Interest;
import lombok.Data;

@Data
public class InterestByAll {

    private Long productId;

    private Double maxRate;

    private String productName;

    private String bankName;

    private String productType;

    public InterestByAll(Interest interest) {
        this.productId = interest.getProduct().getId();
        this.productName = interest.getProduct().getProductName();
        this.maxRate = interest.getRate();
        this.bankName = interest.getProduct().getBankName().getBankName();
        this.productType = interest.getProduct().getProductType().getType();
    }
}
