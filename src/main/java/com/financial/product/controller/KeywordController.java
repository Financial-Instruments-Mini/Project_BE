package com.financial.product.controller;

import com.financial.global.response.BaseResponse;
import com.financial.product.dto.KeywordDTO;
import com.financial.product.entity.enums.Keyword;
import com.financial.product.service.KeywordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"키워드서비스"}, description = "키워드로 조회하는 금융상품 서비스")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @ApiOperation(value = "키워드별 상품목록 조회", notes = "메인화면에서 로그인 없이 확인가능한 키워드별 금융상품목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "금융상품 식별 keyword", required = true),})
    @GetMapping("/{keyword}")
    public BaseResponse<List<KeywordDTO>> searchKeyword(@PathVariable Keyword keyword){
        List<KeywordDTO> result = keywordService.searchKeyword(keyword);

        return BaseResponse.of(result);
    }
}

