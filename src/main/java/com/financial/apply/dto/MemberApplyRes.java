package com.financial.apply.dto;

import com.financial.apply.entity.Apply;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.DueDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberApplyRes {
    private Long productId;
    private BankName bankName;
    private ProductType productType;
    private String productName;
    private Long maxLimit;
    private Long minimumAmount;
    private DueDate dueDate;
    private Double rate;


    public static MemberApplyRes from(Apply apply){
        return MemberApplyRes.builder()
                .productId(apply.getProduct().getId())
                .bankName(apply.getProduct().getBankName())
                .productType(apply.getProduct().getProductType())
                .productName(apply.getProduct().getProductName())
                .maxLimit(apply.getProduct().getMaxLimit())
                .minimumAmount(apply.getProduct().getMinimumAmount())
                .dueDate(apply.getInterest().getDueDate())
                .rate(apply.getInterest().getRate())
                .build();
    }
}
