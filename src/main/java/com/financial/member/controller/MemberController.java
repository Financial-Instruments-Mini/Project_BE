package com.financial.member.controller;

import com.financial.member.dto.MemberRequest;
import com.financial.member.dto.MemberResponse;
import com.financial.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public MemberResponse signup(@RequestBody MemberRequest memberRequest){
        return memberService.signup(memberRequest);
    }


    @GetMapping("/test")
    public String test(){
        return "success";
    }


}
