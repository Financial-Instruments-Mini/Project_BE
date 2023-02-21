package com.financial.member.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    public static String SECRET_KEY;
    public static Long VALID_TIME = 30 * 60 * 1000L; //30분

    public static Long REFRESH_VALID_TIME = 24 * 7 * 60 * 60 * 1000L; //7day
    public static String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret.key}")
    public void setKey(String value) {
        SECRET_KEY = value;
    }
}
