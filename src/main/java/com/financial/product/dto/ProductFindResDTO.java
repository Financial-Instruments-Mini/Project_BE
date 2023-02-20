package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductFindResDTO {
    private Long id;
    private String productName;
    private String bankName;

    private String productType;

    private double maxRate;


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
