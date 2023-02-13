package com.financial.entity.product;

import com.financial.entity.bookmark.BookMark;
import com.financial.entity.product.keyword.Keyword;
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
    @OneToMany
    private List<Keyword> keyword = new ArrayList<>();
    private String content;

}
