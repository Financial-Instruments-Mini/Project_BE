package com.financial.keyword.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.financial.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "keyword_id")
    private Long id;

    @OneToMany(mappedBy = "keyword")
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();
    private String name;

}
