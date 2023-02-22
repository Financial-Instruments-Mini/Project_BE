package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductFindResDTO {
    @ApiModelProperty(name = "productId", dataType = "Long", example = "1")
    private Long id;

    @ApiModelProperty(name = "maxRate", dataType = "Double", example = "3.0")
    private Double maxRate;

    @ApiModelProperty(name = "productName", dataType = "String", example = "신한 미래 농부 적금")
    private String productName;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private String bankName;

    @ApiModelProperty(name = "productType", dataType = "String", example = "DEPOSIT")
    private String productType;


    public ProductFindResDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.bankName = product.getBankName().getBankName();
        this.productType=product.getProductType().getType();
        this.maxRate = findMaxRate(product.getInterests());
    }

    public double findMaxRate(List<Interest> interests) {
        return interests.stream()
                .map(Interest::getRate)
                .mapToDouble(i->i)
                .max()
                .orElse(0);

    }


}
