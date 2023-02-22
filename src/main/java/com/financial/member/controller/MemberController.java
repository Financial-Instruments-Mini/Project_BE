package com.financial.member.controller;

import com.financial.global.response.BaseResponse;
import com.financial.member.dto.*;
import com.financial.member.entity.Member;
import com.financial.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = {"멤버 관련 서비스"} , description = "회원가입, 로그인, 회원의 정보조회 및 수정 서비스를 담당한다.")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원가입 서비스", notes = "회원가입 서비스를 제공한다")
    @PostMapping("/auth/signup")
    public BaseResponse<MemberResponse> signup(@RequestBody @Valid MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.signup(memberRequest);
        return BaseResponse.of(memberResponse);
    }
    @ApiOperation(value = "로그인 서비스", notes = "로그인 서비스를 제공한다")
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

    @ApiOperation(value = "회원정보 수정 서비스", notes = "수정정보를 입력한다(입력한 정보로 수정된다)")
    @PutMapping("/member")
    public BaseResponse<?> updateInfo(@AuthenticationPrincipal MemberAdapter memberAdapter,  @RequestBody @Valid MemberUpdateRequest request){
        Member member = memberAdapter.getMember();
        memberService.updateInfo(request, member.getId());
        return BaseResponse.empty();
    }
    @ApiOperation(value = "회원정보 조회 서비스", notes = "현재 로그인 중인 회원의 정보를 조회한다")
    @GetMapping("/member")
    public BaseResponse<MemberResponse> MemberInfo(@AuthenticationPrincipal MemberAdapter memberAdapter){
        Member member = memberAdapter.getMember();
        MemberResponse memberResponse = MemberResponse.from(member);
        return BaseResponse.of(memberResponse);
    }
    @ApiOperation(value = "토큰 재발급 서비스", notes = "만료된 토큰을 재발급한다.")
    @PostMapping("/auth/refresh")
    public BaseResponse<TokenDto> reIssue(@RequestBody TokenRequestDto tokenRequestDto){
        return BaseResponse.of(memberService.reIssue(tokenRequestDto.getRefreshToken()));
    }

    @ApiOperation(value = "제품 추천 서비스", notes = "회원가입 시 입력받은 설문결과와 회원의 직업에 맞추어 적합한 상품을 추천한다")
    @GetMapping("/member/recommend")
    public BaseResponse<Slice<MemberRecommendDTO>> memberRecommend(@AuthenticationPrincipal MemberAdapter memberAdapter, Pageable pageable) {
        Slice<MemberRecommendDTO> memberRecommend = memberService.getMemberRecommend(pageable, memberAdapter);
        return BaseResponse.of(memberRecommend);
    }
}
