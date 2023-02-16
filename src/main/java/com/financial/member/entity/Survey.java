package com.financial.member.entity;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    private Job job;
    @Enumerated(EnumType.STRING)
    private BankName bankName;

    @Builder
    public Survey(ProductType productType, Job job, BankName bankName) {
        this.productType = productType;
        this.job = job;
        this.bankName = bankName;
    }

    public void updateSurvey(Survey survey){
        if (survey.getProductType() != null) {
            this.productType=survey.getProductType();
        }
        if (survey.getJob() != null) {
            this.job=survey.getJob();
        }
        if (survey.getBankName() != null) {
            this.bankName=survey.getBankName();
        }
    }
}
