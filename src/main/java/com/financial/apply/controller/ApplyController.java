package com.financial.apply.controller;

import com.financial.apply.dto.ApplyRegistrationReq;
import com.financial.apply.dto.MemberProductRes;
import com.financial.apply.service.ApplyService;
import com.financial.global.response.BaseResponse;
import com.financial.member.dto.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("/api/v1/apply")
    public String applyRegistration(@AuthenticationPrincipal MemberAdapter memberAdapter, @RequestBody ApplyRegistrationReq request) {
        return applyService.applyRegistration(memberAdapter, request);
    }

    @GetMapping("/api/v1/apply")
    public BaseResponse<List<MemberProductRes>> memberApply(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        return BaseResponse.of(applyService.memberApply(memberAdapter.getMember().getId()));
    }

    @DeleteMapping("/api/v1/apply/{applyId}")
    public String applyDelete(@AuthenticationPrincipal MemberAdapter memberAdapter, @PathVariable Long applyId) {
        return applyService.deleteApply(memberAdapter, applyId);
    }
}
