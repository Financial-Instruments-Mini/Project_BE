package com.financial.entity.bookmark;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.financial.entity.member.Member;
import com.financial.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookmark_id")
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    private LocalDateTime bookMarkTime;

    public void setMember(Member member) {
        this.member = member;
        member.getBookMarks().add(this);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public static BookMark crateBookMark(Member member, Product... products) {
        BookMark bookMark = new BookMark();
        bookMark.setMember(member);
        for (Product product : products) {
            bookMark.addProduct(product);
        }
        bookMark.setBookMarkTime(LocalDateTime.now());
        return bookMark;
    }

}
