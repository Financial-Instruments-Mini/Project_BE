package com.financial.member.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(name = "email", dataType = "String", example = "a@naver.com")
    private String email;


    @NotBlank
    @ApiModelProperty(name = "password", dataType = "String", example = "aA1234567!")
    private String password;
}
