package com.financial.member.entity;

import com.financial.member.entity.enums.IsMember;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String birthDate;
    @Embedded
    private Survey survey;
    @Enumerated(EnumType.STRING)
    private IsMember isMember;
    private LocalDateTime createdAt;
    
}
