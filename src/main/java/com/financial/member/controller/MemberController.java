package com.financial.member.controller;

import com.financial.global.response.BaseResponse;
import com.financial.member.dto.*;
import com.financial.member.entity.Member;
import com.financial.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/signup")
    public BaseResponse<MemberResponse> signup(@RequestBody @Valid MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.signup(memberRequest);
        return BaseResponse.of(memberResponse);
    }

    @PostMapping("/auth/login")
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


    @PutMapping("/member")
    public BaseResponse<?> updateInfo(@AuthenticationPrincipal MemberAdapter memberAdapter, @RequestBody MemberUpdateRequest request){
        Member member = memberAdapter.getMember();
        memberService.updateInfo(request, member.getId());
        return BaseResponse.empty();
    }

    @GetMapping("/member")
    public BaseResponse<MemberResponse> MemberInfo(@AuthenticationPrincipal MemberAdapter memberAdapter){
        Member member = memberAdapter.getMember();
        MemberResponse memberResponse = MemberResponse.from(member);
        return BaseResponse.of(memberResponse);
    }
}
