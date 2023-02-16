package com.financial.product.entity;

import com.financial.interest.entity.Interest;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import com.financial.product.entity.enums.Keyword;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorColumn(name = "DEPOSIT_OR_SAVING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    private BankName bankName;

    private String productName;

    @Enumerated(EnumType.STRING)
    private JoinWay joinWay;

    private String content;

    @Enumerated(EnumType.STRING)
    private Job job;

    private LocalDate productMakeDay;

    @Enumerated(EnumType.STRING)
    private Keyword keyword;

    private Long minimumAmount;

    private Long maxLimit;

    @OneToMany(mappedBy = "product")
    private List<Interest> interests = new ArrayList<>();

    // 최소금액 있는 생성자
    public Product(ProductType productType, BankName bankName, String productName, JoinWay joinWay, String content, Job job, LocalDate productMakeDay, Keyword keyword, Long minimumAmount, List<Interest> interests) {
        this.productType = productType;
        this.bankName = bankName;
        this.productName = productName;
        this.joinWay = joinWay;
        this.content = content;
        this.job = job;
        this.productMakeDay = productMakeDay;
        this.keyword = keyword;
        this.minimumAmount = minimumAmount;
        this.interests = interests;
    }

    // 최대한도 있는 생성자
    public Product(Long id, ProductType productType, BankName bankName, String productName, JoinWay joinWay, String content, Job job, LocalDate productMakeDay, Keyword keyword, Long maxLimit, List<Interest> interests) {
        this.id = id;
        this.productType = productType;
        this.bankName = bankName;
        this.productName = productName;
        this.joinWay = joinWay;
        this.content = content;
        this.job = job;
        this.productMakeDay = productMakeDay;
        this.keyword = keyword;
        this.maxLimit = maxLimit;
        this.interests = interests;
    }
}

