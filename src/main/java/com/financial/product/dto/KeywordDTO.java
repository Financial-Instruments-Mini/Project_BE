package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KeywordDTO {

    public KeywordDTO(Product product) {
        this.proId = product.getId();
        this.productName = product.getProductName();
        this.minimumAmount = product.getMinimumAmount();
        this.maxLimit = product.getMaxLimit();
        this.bankName = product.getBankName().getBankName();
        this.maxRate = getMaxRate(product.getInterests());
    }


    @ApiModelProperty(name = "productId", dataType = "Long", example = "1")
    private Long proId;

    @ApiModelProperty(name = "productName", dataType = "String", example = "신한 미래 농부 적금")
    private String productName;

    @ApiModelProperty(name = "maxLimit", dataType = "Long", example = "10000000")
    private Long maxLimit;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private String bankName;

    @ApiModelProperty(name = "minimumAmount", dataType = "Long", example = "5000000")
    private Long minimumAmount;

    @ApiModelProperty(name = "maxRate",dataType = "Double", example = "3.0")
    private double maxRate;

    public double getMaxRate(List<Interest> interests) {

        double max = interests.get(0).getRate();

        for (Interest interest : interests) {
            max = Math.max(max, interest.getRate());
        }
        return max;
    }

}

