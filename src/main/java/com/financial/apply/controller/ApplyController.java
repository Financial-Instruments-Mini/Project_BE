package com.financial.apply.controller;

import com.financial.apply.dto.ApplyRegistrationReq;
import com.financial.apply.dto.MemberProductRes;
import com.financial.apply.service.ApplyService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"상품신청서비스"}, description = "금융상품 신청 관련 신청/조회/해지서비스")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply")
public class ApplyController {
    private final ApplyService applyService;

    @ApiOperation(value = "금융상품 신청", notes = "목록에 있는 금융상품을 신청한다.")
    @PostMapping
    public String applyRegistration(@AuthenticationPrincipal MemberAdapter memberAdapter, @RequestBody ApplyRegistrationReq request) {
        return applyService.applyRegistration(memberAdapter, request);
    }

    @ApiOperation(value = "금융상품 신청 목록 조회", notes = "신청한 금융상품 목록을 조회한다.")
    @GetMapping
    public BaseResponse<Slice<MemberProductRes>> memberApply(@AuthenticationPrincipal MemberAdapter memberAdapter, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return BaseResponse.of(applyService.memberApply(memberAdapter.getMember().getId(), pageRequest));
    }
    @ApiOperation(value = "금융상품 신청 해지", notes = "신청한 금융상품을 해지한다.")
    @DeleteMapping("/{applyId}")
    public String applyDelete(@AuthenticationPrincipal MemberAdapter memberAdapter, @PathVariable Long applyId) {
        return applyService.deleteApply(memberAdapter, applyId);
    }

}
