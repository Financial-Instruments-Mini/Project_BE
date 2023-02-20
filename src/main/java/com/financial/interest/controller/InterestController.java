package com.financial.interest.controller;

import com.financial.interest.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

}
