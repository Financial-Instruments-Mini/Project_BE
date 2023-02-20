package com.financial.apply.controller;

import com.financial.apply.dto.ApplyRegistrationReq;
import com.financial.apply.dto.MemberProductRes;
import com.financial.apply.service.ApplyService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply")
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping
    public String applyRegistration(@AuthenticationPrincipal MemberAdapter memberAdapter, @RequestBody ApplyRegistrationReq request) {
        return applyService.applyRegistration(memberAdapter, request);
    }

    @GetMapping
    public BaseResponse<Slice<MemberProductRes>> memberApply(@AuthenticationPrincipal MemberAdapter memberAdapter, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return BaseResponse.of(applyService.memberApply(memberAdapter.getMember().getId(), pageRequest));
    }

    @DeleteMapping("/{applyId}")
    public String applyDelete(@AuthenticationPrincipal MemberAdapter memberAdapter, @PathVariable Long applyId) {
        return applyService.deleteApply(memberAdapter, applyId);
    }

}
