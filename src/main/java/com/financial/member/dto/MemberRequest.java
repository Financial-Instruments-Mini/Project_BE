package com.financial.member.dto;

import com.financial.member.entity.Member;
import com.financial.member.entity.Survey;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@Setter
@Getter
public class MemberRequest {


    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    @ApiModelProperty(name = "email", dataType = "String", example = "a@naver.com")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,12}", message = "비밀번호는 8~12자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @ApiModelProperty(name = "password", dataType = "String", example = "aA123456!")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @ApiModelProperty(name = "name", dataType = "String", example = "홍길동")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @ApiModelProperty(name = "phoneNumber", dataType = "String", example = "01012345678")
    private String phoneNumber;

    @NotBlank(message = "생년월일은 필수 입력 값입니다.")
    @ApiModelProperty(name = "birthDate", dataType = "String", example = "19990517")
    private String birthDate;

    @ApiModelProperty(name = "productType", dataType = "ProductType", example = "DEPOSIT")
    private ProductType productType;

    @ApiModelProperty(name = "job", dataType = "Job", example = "PROFESSION")
    private Job job;

    @ApiModelProperty(name = "bankName", dataType = "BankName", example = "국민")
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