package com.financial.product.service;

import com.financial.product.dto.ProductDetailResponseDTO;
import com.financial.product.entity.Product;
import com.financial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDetailResponseDTO productsDetail(Long productId) {
        Product findProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException());
        ProductDetailResponseDTO detailResponse = new ProductDetailResponseDTO(findProduct);
        return detailResponse;
    }
}
