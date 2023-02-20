package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import lombok.Data;

@Data
public class InterestDto {

    private Long id;
    private int dueDate;
    private Double rate;


    public InterestDto(Interest interest) {
        this.id = interest.getId();
        this.dueDate = interest.getDueDate().getInterest();
        this.rate = interest.getRate();
    }
}
