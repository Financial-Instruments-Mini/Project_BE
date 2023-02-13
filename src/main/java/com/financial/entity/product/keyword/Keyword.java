package com.financial.entity.product.keyword;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "keyword_id")
    @JoinColumn(name = "product_id")
    private Long id;

    private String name;

}
