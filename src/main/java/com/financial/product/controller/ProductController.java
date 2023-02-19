package com.financial.product.controller;

import com.financial.global.response.BaseResponse;
import com.financial.product.dto.ProductDetailResponseDTO;
import com.financial.product.dto.ProductListDto;
import com.financial.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public BaseResponse<Slice<ProductListDto>> allProductList(
            @RequestParam(value = "sort", required = false, defaultValue = "interest") String sort,
            @PageableDefault(size = 5) Pageable pageable) {
        return new BaseResponse<>(productService.allProductList(pageable, sort));
    }


    @GetMapping("/{products_id}")
    public BaseResponse<ProductDetailResponseDTO> productsDetail(@PathVariable Long products_id) {
        ProductDetailResponseDTO productsDetail = productService.productsDetail(products_id);
        return BaseResponse.of(productsDetail);
    }
}
