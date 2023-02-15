package com.financial.member.dto;

import com.financial.member.entity.Member;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MemberResponse {

    private Long memberId;
    private String accessToken;

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String birthDate;

    private ProductType productType;
    private Job job;
    private BankName bankName;

    public static MemberResponse from(Member member){
        return MemberResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .birthDate(member.getBirthDate())
                .productType(member.getSurvey().getProductType())
                .job(member.getSurvey().getJob())
                .bankName(member.getSurvey().getBankName())
                .build();
    }
}