package com.financial.member.service;

import com.financial.member.dto.MemberAdapter;
import com.financial.member.entity.Member;
import com.financial.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(
                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.")
        );

        //return MemberDetails.of(member);
        return new MemberAdapter(member);
    }
}
