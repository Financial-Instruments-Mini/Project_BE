package com.financial.apply.dto;

import com.financial.apply.entity.Apply;
import com.financial.bookmark.entity.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberProductRes {
    private Long productId;
    private String bankName;
    private String productType;
    private String productName;
    private Long maxLimit;
    private Long minimumAmount;
    private int dueDate;
    private Double rate;


    public static MemberProductRes fromApply(Apply apply){
        return MemberProductRes.builder()
                .productId(apply.getProduct().getId())
                .bankName(apply.getProduct().getBankName().getBankName())
                .productType(apply.getProduct().getProductType().getType())
                .productName(apply.getProduct().getProductName())
                .maxLimit(apply.getProduct().getMaxLimit())
                .minimumAmount(apply.getProduct().getMinimumAmount())
                .dueDate(apply.getInterest().getDueDate().getInterest())
                .rate(apply.getInterest().getRate())
                .build();
    }

    public static MemberProductRes fromBookmark(Bookmark bookmark){
        return MemberProductRes.builder()
                .productId(bookmark.getProduct().getId())
                .bankName(bookmark.getProduct().getBankName().getBankName())
                .productType(bookmark.getProduct().getProductType().getType())
                .productName(bookmark.getProduct().getProductName())
                .maxLimit(bookmark.getProduct().getMaxLimit())
                .minimumAmount(bookmark.getProduct().getMinimumAmount())
                .dueDate(bookmark.getInterest().getDueDate().getInterest())
                .rate(bookmark.getInterest().getRate())
                .build();
    }
}
