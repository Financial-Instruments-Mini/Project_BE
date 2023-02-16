package com.financial.interest.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.Product;
import com.financial.product.entity.enums.DueDate;
import lombok.Data;

import javax.persistence.*;

@Data
public class InterestResponseDTO {

    private Long id;

    private int dueDate;

    private Double rate;

    public InterestResponseDTO(Interest interest) {
        this.id = interest.getId();
        this.dueDate = interest.getDueDate().getInterest();
        this.rate = interest.getRate();
    }
}
