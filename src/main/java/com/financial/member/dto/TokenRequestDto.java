package com.financial.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenRequestDto {

    private String refreshToken;

    @Builder
    public TokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
