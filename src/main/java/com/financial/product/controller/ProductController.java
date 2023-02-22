package com.financial.product.controller;

import com.financial.global.response.BaseResponse;
import com.financial.interest.dto.InterestByAll;
import com.financial.product.dto.ProductDetailResponseDTO;
import com.financial.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
@Api(tags = {"상품조회 서비스"}, description = "모든 상품 조회, 상품아이디로 상품을 조회 " +
        "그리고 상품을 등록한 시간과 상품의 금리순으로 조회하는 서비스를 담당한다.")
public class ProductController {

    private final ProductService productService;

////    @GetMapping("/")
//    public BaseResponse<Slice<ProductListDto>> allProductList(
//            @RequestParam(value = "sort", required = false, defaultValue = "interest") String sort,
//            @PageableDefault(size = 5) Pageable pageable) {
//        return new BaseResponse<>(productService.allProductList(pageable, sort));
//    }

    @ApiOperation(value = "전체 상품 조회", notes = "전체 상품을 조회한다.")
    @ApiImplicitParam(name = "sort", value = "등록된 상품의 정렬 기준(금리순, 최신순)")
    @GetMapping
    public BaseResponse<?> allProduct(Pageable pageable, @RequestParam(value = "sort", required = false) String sort) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        if (sort.equals("last")) {
            Slice<InterestByAll> sortedProduct = productService.findALlProductSortByMakeDay(pageRequest);
            return BaseResponse.of(sortedProduct);
        }
        Slice<InterestByAll> sortedProduct = productService.findAllProductSortByInterest(pageRequest);
        return BaseResponse.of(sortedProduct);
    }

    @ApiOperation(value = "상품아이디로 특정 상품을 조회", notes = "특정 상품을 조회한다.")
    @ApiImplicitParam(name = "products_id", value = "등록된 상품의 아이디", dataType = "Long" , required = true)
    @GetMapping("/{products_id}")
    public BaseResponse<ProductDetailResponseDTO> productsDetail(@PathVariable Long products_id) {
        ProductDetailResponseDTO productsDetail = productService.productsDetail(products_id);
        return BaseResponse.of(productsDetail);
    }
    @ApiOperation(value = "검색어로 입력받은 값과 일치하는 상품을 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "상품이름", dataType = "String", required = true),
            @ApiImplicitParam(name = "sort", value = "정렬순(금리순, 최신순)", dataType = "String")
    })
    @GetMapping("/search")
    public BaseResponse<?> productSearch(
            Pageable pageable,
            @RequestParam String search,
            @RequestParam(required = false) String sort
    ) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        if (sort.equals("last")){
            Slice<InterestByAll> productSearch = productService.findProductSearchByMakeDay(pageRequest,search);
            return BaseResponse.of(productSearch);
        }
        Slice<InterestByAll> productSearch = productService.findProductSearchByInterest(pageRequest,search);
        return BaseResponse.of(productSearch);
    }
}
