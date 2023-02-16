package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.Product;
import com.financial.product.entity.enums.BankName;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


    private Long proId;

    private String productName;

    private Long maxLimit;

    private String bankName;
    private Long minimumAmount;

    private double maxRate;

    public double getMaxRate(List<Interest> interests) {

        double max = interests.get(0).getRate();

        for (Interest interest : interests) {
            max = Math.max(max, interest.getRate());
        }
        return max;
    }

}

