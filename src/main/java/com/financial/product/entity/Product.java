package com.financial.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.financial.interest.entity.Interest;
import com.financial.keyword.entity.Keyword;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DEPOSIT_OR_SAVING")
@Getter
@Setter
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private BankName bankName;
    private String productName;
    @Enumerated(EnumType.STRING)
    private JoinWay joinWay;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    @JsonBackReference
    private Keyword keyword;
    @OneToMany
    private List<Interest> interests = new ArrayList<>();
}
