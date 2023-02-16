package com.financial.member.entity;

import com.financial.member.entity.enums.IsMember;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

//1번 아이디 글자 수
//        아이디 글자 패턴
//        아이디 = email
//        2번 비밀번호
//        글자수 = 8이상 12자 이하
//        패턴 = 영문 숫자 특수문자
//        최소 1글자씩
//        이름은 한글만 2글자 이상
//        생년월일 19990909
//        01012341234
@Entity
@ToString
@Getter
@DynamicInsert //insert 시 지정된 default 값 적용 시킴
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String birthDate;
    @Embedded
    private Survey survey;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'Y'")
    private IsMember isMember;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Member(String email, String password, String name, String phoneNumber, String birthDate, Survey survey) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.survey = survey;
    }
}

