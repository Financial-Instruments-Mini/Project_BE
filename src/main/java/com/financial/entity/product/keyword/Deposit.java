package com.financial.entity.product.keyword;

import com.financial.entity.member.ProductType;
import com.financial.entity.product.Product;
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
