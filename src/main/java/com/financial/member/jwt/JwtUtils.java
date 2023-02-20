package com.financial.member.jwt;

import com.financial.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final UserDetailsService userDetailsService;

    public String createToken(Member member){
        Claims claims = Jwts.claims().setSubject(member.getEmail());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("ticcle")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+JwtProperties.VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET_KEY)
                .compact();
    }


    private Claims getClaimsFromToken(String  token){
        return Jwts.parser()
                .setSigningKey(JwtProperties.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getSubject(String token){
        return Jwts.parser()
                .setSigningKey(JwtProperties.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }

    public boolean isValidToken(String token){
        try {
            if (token == null) {
                return false;
            }
            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        }catch (JwtException | NullPointerException exception){
            return false;
        }
    }


}

