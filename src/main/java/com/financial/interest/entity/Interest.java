package com.financial.interest.entity;

import com.financial.product.entity.Product;
import com.financial.product.entity.enums.DueDate;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private DueDate dueDate;
    private Double rate;

    public Interest(Product product, DueDate dueDate, Double rate) {
        this.product = product;
        this.dueDate = dueDate;
        this.rate = rate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
