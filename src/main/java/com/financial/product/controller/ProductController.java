package com.financial.product.controller;

import com.financial.global.response.BaseResponse;
import com.financial.interest.dto.InterestByAll;
import com.financial.product.dto.ProductDetailResponseDTO;
import com.financial.product.service.ProductService;
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
public class ProductController {

    private final ProductService productService;

////    @GetMapping("/")
//    public BaseResponse<Slice<ProductListDto>> allProductList(
//            @RequestParam(value = "sort", required = false, defaultValue = "interest") String sort,
//            @PageableDefault(size = 5) Pageable pageable) {
//        return new BaseResponse<>(productService.allProductList(pageable, sort));
//    }

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

    @GetMapping("/{products_id}")
    public BaseResponse<ProductDetailResponseDTO> productsDetail(@PathVariable Long products_id) {
        ProductDetailResponseDTO productsDetail = productService.productsDetail(products_id);
        return BaseResponse.of(productsDetail);
    }

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
