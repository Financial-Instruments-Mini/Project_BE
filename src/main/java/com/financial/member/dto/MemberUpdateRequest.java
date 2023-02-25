package com.financial.member.dto;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class MemberUpdateRequest {


    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,12}", message = "비밀번호는 8~12자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @ApiModelProperty(name = "password", dataType = "String", example = "aA123456!")
    private String password;

    @ApiModelProperty(name = "phoneNumber", dataType = "String", example = "01012345678")
    private String phoneNumber;

    @ApiModelProperty(name = "productType", dataType = "ProductType", example = "DEPOSIT")
    private ProductType productType;

    @ApiModelProperty(name = "job", dataType = "Job", example = "PROFESSION")
    private Job job;

    @ApiModelProperty(name = "bankName", dataType = "BankName", example = "국민")
    private BankName bankName;

}
