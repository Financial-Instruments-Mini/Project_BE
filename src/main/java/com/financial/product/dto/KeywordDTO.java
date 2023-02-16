package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.Deposit;
import com.financial.product.entity.Saving;

import com.financial.product.entity.enums.BankName;
import lombok.Getter;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class KeywordDTO {

    public KeywordDTO(Saving saving) {
        this.proId = saving.getId();
        this.productName = saving.getProductName();
        this.maxLimit = saving.getMaxLimit();
        this.bankName = saving.getBankName();
        this.maxRate = getMaxRate(saving.getInterests());
    }

    public KeywordDTO(Deposit deposit) {
        this.proId = deposit.getId();
        this.productName = deposit.getProductName();
        this.minimumAmount = deposit.getMinimumAmount();
        this.bankName = deposit.getBankName();
        this.maxRate = getMaxRate(deposit.getInterests());
    }

    private Long proId;

   private String productName;

    private Long maxLimit;

    private BankName bankName;
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
