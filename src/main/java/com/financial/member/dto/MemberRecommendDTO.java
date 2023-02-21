package com.financial.member.dto;

import com.financial.interest.entity.Interest;
import lombok.Data;

@Data
public class MemberRecommendDTO {
    private Long productId;
    private Double maxRate;
    private String productName;
    private String bankName;
    private String productType;

    public MemberRecommendDTO(Interest interest) {
        this.productId = interest.getProduct().getId();
        this.maxRate = interest.getRate();
        this.productName = interest.getProduct().getProductName();
        this.bankName = interest.getProduct().getBankName().getBankName();
        this.productType = interest.getProduct().getProductType().getType();
    }
}
