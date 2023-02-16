package com.financial.member.dto;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberUpdateRequest {


    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,12}", message = "비밀번호는 8~12자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    private String phoneNumber;
    private ProductType productType;
    private Job job;
    private BankName bankName;

}
