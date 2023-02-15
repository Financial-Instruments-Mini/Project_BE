package com.financial.member.service;

import com.financial.member.dto.MemberRequest;
import com.financial.member.dto.MemberResponse;
import com.financial.member.entity.Member;
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

    public MemberResponse signup(MemberRequest memberRequest){
        if (isDuplicate(memberRequest.getEmail())){
            return null;
        }
        memberRequest.encodePassword(passwordEncoder);
        Member member = memberRepository.save(memberRequest.toEntity());
        return MemberResponse.from(member);
    }

    public boolean isDuplicate(String email){
        return memberRepository.existsByEmail(email);
    }
}
