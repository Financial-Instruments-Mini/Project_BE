package com.financial.member.service;

import com.financial.member.dto.*;
import com.financial.member.entity.Member;
import com.financial.member.entity.RefreshToken;
import com.financial.member.jwt.JwtUtils;
import com.financial.member.repository.MemberRepository;
import com.financial.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponse signup(MemberRequest memberRequest) {
        if (isDuplicate(memberRequest.getEmail())) {
            return null;
        }
        memberRequest.encodePassword(passwordEncoder);
        Member member = memberRepository.save(memberRequest.toEntity());
        MemberResponse memberResponse = MemberResponse.from(member);
        Date date = new Date();
        String accessToken = jwtUtils.createToken(member);
        String refreshToken = jwtUtils.createRefreshToken(member.getId());

        memberResponse.setTokenDto(
                TokenDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessStartTime(String.valueOf(date))
                        .accessExpirationTime(jwtUtils.getExpiration(accessToken))
                        .build()
        );

        RefreshToken originRefreshToken = refreshTokenRepository.findByMemberId(member.getId()).orElse(null);
        if (originRefreshToken == null) {
            RefreshToken refreshTokenEn = RefreshToken.builder().memberId(member.getId()).token(refreshToken).build();
            refreshTokenRepository.save(refreshTokenEn);
        } else {
            originRefreshToken.updateToken(refreshToken);
        }
        //memberResponse.setAccessToken(jwtUtils.createToken(member));
        return memberResponse;
    }

    @Transactional
    public MemberResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (member == null) {
            return null;
        }

        MemberResponse memberResponse = MemberResponse.from(member);

        if (passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            Date date = new Date();
            String accessToken = jwtUtils.createToken(member);
            String refreshToken = jwtUtils.createRefreshToken(member.getId());
            memberResponse.setTokenDto(
                    TokenDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .accessStartTime(String.valueOf(date))
                            .accessExpirationTime(jwtUtils.getExpiration(accessToken))
                            .build());

            RefreshToken originRefreshToken = refreshTokenRepository.findByMemberId(member.getId()).orElse(null);
            if (originRefreshToken == null) {
                RefreshToken refreshTokenEn = RefreshToken.builder().memberId(member.getId()).token(refreshToken).build();
                refreshTokenRepository.save(refreshTokenEn);
            } else {
                originRefreshToken.updateToken(refreshToken);
            }
        }
        return memberResponse;
    }

    @Transactional
    public TokenDto reIssue(String refreshToken) {
        if (!jwtUtils.isValidToken(refreshToken)) {
            throw new IllegalArgumentException("refresh토큰 만료됨");
        }

        Long memberId = Long.valueOf(jwtUtils.getSubject(refreshToken));
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        RefreshToken originRefreshToken = refreshTokenRepository.findByMemberId(memberId).orElseThrow(
                () -> new IllegalArgumentException("토큰을 찾을 수 없습니다.")
        );

        if (!originRefreshToken.getToken().equals(refreshToken)) {
            throw new IllegalArgumentException("리프레시 토큰이 불일치 합니다.");
        }

        Date date = new Date();
        String accessToken = jwtUtils.createToken(member);
        String newRefreshToken = jwtUtils.createRefreshToken(memberId);
        originRefreshToken.updateToken(newRefreshToken);
        refreshTokenRepository.save(originRefreshToken);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(originRefreshToken.getToken())
                .accessStartTime(String.valueOf(date))
                .accessExpirationTime(jwtUtils.getExpiration(accessToken))
                .build();


    }


    @Transactional
    public void updateInfo(MemberUpdateRequest request, Long memberId) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        memberRepository.findById(memberId).ifPresent(member -> member.updateMember(request));
    }

    public boolean isDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }
}
