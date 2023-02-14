package com.financial.interest.entity;

import com.financial.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Interest {

    @Id
    @GeneratedValue
    @Column(name = "interest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private String expirationMonth;
    private Double rate;


}
