package com.financial.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    @ApiModelProperty(name = "accessToken", dataType = "String")
    private String accessToken;

    @ApiModelProperty(name = "refreshToken", dataType = "String")
    private String refreshToken;

    @ApiModelProperty(name = "accessStartTime", dataType = "String")
    private String accessStartTime;

    @ApiModelProperty(name = "accessExpirationTime", dataType = "String")
    private String accessExpirationTime;

    @Builder
    public TokenDto(String accessToken, String refreshToken, String accessStartTime, String accessExpirationTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessStartTime = accessStartTime;
        this.accessExpirationTime = accessExpirationTime;
    }
}
