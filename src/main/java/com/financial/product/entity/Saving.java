package com.financial.product.entity;

import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.InterestRate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("SAVING")
@Getter
@Setter
public class Saving extends Product {
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private InterestRate interestRate;

    private Double rate;
}
