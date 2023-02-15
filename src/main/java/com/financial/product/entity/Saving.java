package com.financial.product.entity;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("SAVING")
@Getter
@Setter
@NoArgsConstructor
public class Saving extends Product {

    private Long maxLimit;

    @Builder

    public Saving(ProductType productType, BankName bankName, String productName, JoinWay joinWay, String content, Job job, Long maxLimit) {
        super(productType, bankName, productName, joinWay, content, job);
        this.maxLimit = maxLimit;
    }
}
