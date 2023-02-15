package com.financial.member.dto;

import com.financial.member.entity.Member;
import com.financial.member.entity.Survey;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
public class MemberRequest {


    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String birthDate;

    private ProductType productType;
    private Job job;
    private BankName bankName;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .survey(Survey.builder()
                        .productType(productType)
                        .job(job)
                        .bankName(bankName)
                        .build())
                .build();
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}