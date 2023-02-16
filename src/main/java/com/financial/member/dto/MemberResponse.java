package com.financial.member.dto;

import com.financial.member.entity.Member;
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

    private String productType;
    private String job;
    private String bankName;

    public static MemberResponse from(Member member){
        if (member.getSurvey() == null) {
            return MemberResponse.builder()
                    .memberId(member.getId())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .name(member.getName())
                    .phoneNumber(member.getPhoneNumber())
                    .birthDate(member.getBirthDate())
                    .build();
        }
        return MemberResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .birthDate(member.getBirthDate())
                .productType(member.getSurvey().getProductType().getType())
                .job(member.getSurvey().getJob().getJobName())
                .bankName(member.getSurvey().getBankName().getBankName())
                .build();
    }
}