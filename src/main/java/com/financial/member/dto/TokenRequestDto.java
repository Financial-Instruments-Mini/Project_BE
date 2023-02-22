package com.financial.member.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenRequestDto {

    @ApiModelProperty(name = "refreshToken", dataType = "String")
    private String refreshToken;

    @Builder
    public TokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
