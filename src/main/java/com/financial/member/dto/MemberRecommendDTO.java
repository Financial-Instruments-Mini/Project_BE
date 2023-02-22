package com.financial.member.dto;

import com.financial.interest.entity.Interest;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class MemberRecommendDTO {
    @ApiModelProperty(name = "productId", dataType = "Long", example = "1")
    private Long productId;

    @ApiModelProperty(name = "maxRate", dataType = "Double", example = "3.0")
    private Double maxRate;

    @ApiModelProperty(name = "productName", dataType = "String", example = "신한 미래 농부 적금")
    private String productName;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private String bankName;

    @ApiModelProperty(name = "productType", dataType = "ProductType", example = "DEPOSIT")
    private String productType;

    public MemberRecommendDTO(Interest interest) {
        this.productId = interest.getProduct().getId();
        this.maxRate = interest.getRate();
        this.productName = interest.getProduct().getProductName();
        this.bankName = interest.getProduct().getBankName().getBankName();
        this.productType = interest.getProduct().getProductType().getType();
    }
}
