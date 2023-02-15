package com.financial.product.entity;

import com.financial.interest.entity.Interest;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DEPOSIT_OR_SAVING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "product")
    private List<Interest> interests = new ArrayList<>();


    public Product(ProductType productType,BankName bankName, String productName, JoinWay joinWay, String content, Job job) {
        this.productType = productType;
        this.bankName = bankName;
        this.productName = productName;
        this.joinWay = joinWay;
        this.content = content;
        this.job = job;
   }

}
