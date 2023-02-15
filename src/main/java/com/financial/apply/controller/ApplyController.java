package com.financial.apply.controller;

import com.financial.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;
}
