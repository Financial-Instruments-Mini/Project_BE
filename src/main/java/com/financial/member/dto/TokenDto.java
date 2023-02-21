package com.financial.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String accessToken;
    private String refreshToken;

    private String accessStartTime;

    private String accessExpirationTime;

    @Builder
    public TokenDto(String accessToken, String refreshToken, String accessStartTime, String accessExpirationTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessStartTime = accessStartTime;
        this.accessExpirationTime = accessExpirationTime;
    }
}
