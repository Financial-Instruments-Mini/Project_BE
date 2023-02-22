package com.financial.apply.dto;

import com.financial.apply.entity.Apply;
import com.financial.bookmark.entity.Bookmark;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberProductRes {

    @ApiModelProperty(name = "productId",dataType = "Long", example = "1")
    private Long productId;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private String bankName;

    @ApiModelProperty(name = "productType", dataType = "String", example = "DEPOSIT")
    private String productType;

    @ApiModelProperty(name = "productName", dataType = "String", example = "KB 팜팜대로 적금")
    private String productName;

    @ApiModelProperty(name = "maxLimit", dataType = "Long", example = "50000000")
    private Long maxLimit;

    @ApiModelProperty(name = "minimumAmount", dataType = "Long", example = "1000000")
    private Long minimumAmount;

    @ApiModelProperty(name = "dueDate", dataType = "int", example = "TWELVE")
    private int dueDate;

    @ApiModelProperty(name = "rate", example = "2.5")
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
