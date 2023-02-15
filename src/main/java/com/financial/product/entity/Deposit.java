package com.financial.product.entity;

import com.financial.interest.entity.Interest;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import com.financial.product.entity.enums.Keyword;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("DEPOSIT")
@NoArgsConstructor
@Getter
@Setter
public class Deposit extends Product {

    private Long minimumAmount;

    public String convertMinimumAmount() {
        return "최소금액은 " +minimumAmount + "원 입니다.";
    }

    @Builder
    public Deposit(ProductType productType, BankName bankName, String productName, JoinWay joinWay, String content, Job job, LocalDate productMakeDay, Keyword keyword, Long minimumAmount) {
        super(productType, bankName, productName, joinWay, content, job, productMakeDay, keyword);
        this.minimumAmount = minimumAmount;
    }


}
