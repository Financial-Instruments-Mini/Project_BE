package com.financial.product.entity;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("DEPOSIT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deposit extends Product {
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Long maxLimit;

    @Builder
    public Deposit(BankName bankName, String productName, JoinWay joinWay, String content, Job job, ProductType productType, Long maxLimit) {
        super(bankName, productName, joinWay, content, job);
        this.productType = productType;
        this.maxLimit = maxLimit;
    }
}
