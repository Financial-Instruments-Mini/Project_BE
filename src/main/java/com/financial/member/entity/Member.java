package com.financial.member.entity;

import com.financial.member.dto.MemberUpdateRequest;
import com.financial.member.entity.enums.IsMember;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public void updateMember(MemberUpdateRequest request){
        Survey surveyRequest = Survey.builder()
                .productType(request.getProductType())
                .job(request.getJob())
                .bankName(request.getBankName())
                .build();
        if (request.getPassword() != null) {
            this.password=request.getPassword();
        }
        if (request.getPhoneNumber() != null) {
            this.phoneNumber=request.getPhoneNumber();
        }
        this.survey.updateSurvey(surveyRequest);
    }
}

