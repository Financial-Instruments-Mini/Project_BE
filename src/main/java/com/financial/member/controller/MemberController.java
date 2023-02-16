package com.financial.member.controller;

import com.financial.global.response.BaseResponse;
import com.financial.member.dto.*;
import com.financial.member.entity.Member;
import com.financial.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponse<MemberResponse> signup(@RequestBody @Valid MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.signup(memberRequest);
        return BaseResponse.of(memberResponse);
    }

    @PostMapping("/login")
    public BaseResponse<MemberResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        MemberResponse memberResponse = memberService.login(loginRequest);
        return BaseResponse.of(memberResponse);
    }

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        Member member = memberAdapter.getMember();
        System.out.println(member.toString());
        return "success";
    }


}
