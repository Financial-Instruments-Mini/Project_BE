package com.financial.bookmark.entity;

import com.financial.interest.entity.Interest;
import com.financial.member.entity.Member;
import com.financial.product.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id")
    private Interest interest;

    private LocalDateTime bookMarkTime;

    public void setMember(Member member) {
        this.member = member;
    }

    public void addProduct(Product product) {
        this.product = product;
    }

    public Bookmark crateBookMark(Member member, Product product, Interest interest) {
        this.member = member;
        this.product = product;
        this.interest = interest;
        this.bookMarkTime = LocalDateTime.now();
        return this;
    }

}

