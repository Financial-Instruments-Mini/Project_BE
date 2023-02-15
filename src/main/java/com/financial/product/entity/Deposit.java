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
@Getter
@Setter
public class Deposit extends Product {


    @Builder
    public Deposit(ProductType productType, BankName bankName, String productName, JoinWay joinWay, String content, Job job) {
        super(productType, bankName, productName, joinWay, content, job);
    }
}
