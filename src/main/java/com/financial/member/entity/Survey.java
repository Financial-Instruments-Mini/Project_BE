package com.financial.member.entity;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Survey {
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    private Job job;
    private String mainBank;

}
