package com.financial.interest.dto;

import com.financial.interest.entity.Interest;
import lombok.Data;

@Data
public class InterestByAll {

    private Long productId;

    private Double rate;

    private String productName;

    public InterestByAll(Interest interest) {
        this.productId = interest.getProduct().getId();
        this.productName = interest.getProduct().getProductName();
        this.rate = interest.getRate();
    }
}
