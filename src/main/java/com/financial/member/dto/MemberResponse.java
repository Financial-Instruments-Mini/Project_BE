package com.financial.member.dto;

import com.financial.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MemberResponse {


    @ApiModelProperty(name = "memberId", dataType = "Long", example = "1")
    private Long memberId;
//    private String accessToken;

    private TokenDto tokenDto;

    @ApiModelProperty(name = "email", dataType = "String", example = "a@naver.com")
    private String email;

    @ApiModelProperty(name = "password", dataType = "String", example = "aA123456!")
    private String password;

    @ApiModelProperty(name = "name", dataType = "String", example = "홍길동")
    private String name;

    @ApiModelProperty(name = "phoneNumber", dataType = "String", example = "01012345678")
    private String phoneNumber;

    @ApiModelProperty(name = "birthDate", dataType = "String", example = "19990517")
    private String birthDate;

    @ApiModelProperty(name = "productType", dataType = "ProductType", example = "DEPOSIT")
    private String productType;

    @ApiModelProperty(name = "job", dataType = "Job", example = "PROFESSION")
    private String job;

    @ApiModelProperty(name = "bankName", dataType = "BankName", example = "국민")
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