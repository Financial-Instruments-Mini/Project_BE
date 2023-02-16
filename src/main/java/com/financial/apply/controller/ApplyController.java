package com.financial.apply.controller;

import com.financial.apply.service.ApplyService;
import com.financial.apply.dto.ApplyRegistrationReq;
import com.financial.member.dto.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("/api/v1/apply")
    public String applyRegistration(@AuthenticationPrincipal MemberAdapter memberAdapter,@RequestBody ApplyRegistrationReq request) {
        return applyService.applyRegistration(memberAdapter, request);
    }
}
