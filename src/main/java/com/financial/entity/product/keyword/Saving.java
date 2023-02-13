package com.financial.entity.product.keyword;

import com.financial.entity.product.InterestRate;
import com.financial.entity.member.ProductType;
import com.financial.entity.product.Product;
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
