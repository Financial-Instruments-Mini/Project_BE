package com.financial.product.entity;

import com.financial.member.entity.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("DEPOSIT")
@Getter
@Setter
public class Deposit extends Product {
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Long maxLimit;
    private String expirationMonth;
    private Double rate;

}
