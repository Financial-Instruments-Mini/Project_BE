package com.financial.member.service;

import com.financial.member.dto.LoginRequest;
import com.financial.member.dto.MemberRequest;
import com.financial.member.dto.MemberResponse;
import com.financial.member.entity.Member;
import com.financial.member.jwt.JwtUtils;
import com.financial.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    public MemberResponse signup(MemberRequest memberRequest){
        if (isDuplicate(memberRequest.getEmail())){
            return null;
        }
        memberRequest.encodePassword(passwordEncoder);
        Member member = memberRepository.save(memberRequest.toEntity());
        MemberResponse memberResponse = MemberResponse.from(member);
        memberResponse.setAccessToken(jwtUtils.createToken(member));
        return memberResponse;
    }

    public MemberResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (member == null) {
            return null;
        }

        MemberResponse memberResponse = MemberResponse.from(member);

        if (passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())){
            String token = jwtUtils.createToken(member);
            memberResponse.setAccessToken(token);
        }
        return memberResponse;
    }

    public boolean isDuplicate(String email){
        return memberRepository.existsByEmail(email);
    }
}
