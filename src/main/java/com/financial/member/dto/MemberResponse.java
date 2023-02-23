package com.financial.member.dto;

import com.financial.member.entity.Member;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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
        Optional<ProductType> optionalProductType = Optional.ofNullable(member.getSurvey().getProductType());
        Optional<Job> optionalJob = Optional.ofNullable(member.getSurvey().getJob());
        Optional<BankName> optionalBankName = Optional.ofNullable(member.getSurvey().getBankName());
        return MemberResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .birthDate(member.getBirthDate())
                .productType(optionalProductType.map(ProductType::getType).orElse(null))
                .job(optionalJob.map(Job::getJobName).orElse(null))
                .bankName(optionalBankName.map(BankName::getBankName).orElse(null))
                .build();
    }
}