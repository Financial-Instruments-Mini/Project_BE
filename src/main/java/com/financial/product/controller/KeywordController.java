package com.financial.product.controller;

import com.financial.global.response.BaseResponse;
import com.financial.product.dto.KeywordDTO;
import com.financial.product.entity.enums.Keyword;
import com.financial.product.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/{keyword}")
    public BaseResponse<List<KeywordDTO>> searchKeyword(@PathVariable Keyword keyword){
        List<KeywordDTO> result = keywordService.searchKeyword(keyword);

        return BaseResponse.of(result);
    }
}

