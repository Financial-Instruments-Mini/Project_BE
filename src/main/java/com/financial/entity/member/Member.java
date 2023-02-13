package com.financial.entity.member;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.financial.entity.bookmark.BookMark;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private int age;
    @Embedded
    private Survey survey;
    @Enumerated(EnumType.STRING)
    private IsMember isMember;
    private LocalDateTime createdAt;
    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<BookMark> bookMarks = new ArrayList<>();
    
}
