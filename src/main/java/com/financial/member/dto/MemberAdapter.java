package com.financial.member.dto;

import com.financial.member.entity.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class MemberAdapter extends User {

    private Member member;


    public MemberAdapter(Member member) {
        super(member.getEmail(), member.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
