package com.financial.product.controller;

import com.financial.global.response.BaseResponse;
import com.financial.product.dto.ProductDetailResponseDTO;
import com.financial.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{products_id}")
    public BaseResponse<ProductDetailResponseDTO> productsDetail(@PathVariable Long products_id) {
        ProductDetailResponseDTO productsDetail = productService.productsDetail(products_id);
        return BaseResponse.of(productsDetail);
    }
}
