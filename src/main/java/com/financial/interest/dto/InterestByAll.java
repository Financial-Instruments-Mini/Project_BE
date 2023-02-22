package com.financial.interest.dto;

import com.financial.interest.entity.Interest;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class InterestByAll {
    @ApiModelProperty(name = "productId",dataType = "Long", example = "1")
    private Long productId;

    @ApiModelProperty(name = "maxRate",dataType = "Double", example = "3.0")
    private Double maxRate;

    @ApiModelProperty(name = "productName",dataType = "String", example = "KB 팜팜대로 적금")
    private String productName;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private String bankName;

    @ApiModelProperty(name = "productType", dataType = "String", example = "DEPOSIT")
    private String productType;

    public InterestByAll(Interest interest) {
        this.productId = interest.getProduct().getId();
        this.productName = interest.getProduct().getProductName();
        this.maxRate = interest.getRate();
        this.bankName = interest.getProduct().getBankName().getBankName();
        this.productType = interest.getProduct().getProductType().getType();
    }
}
