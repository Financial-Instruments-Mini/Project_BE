package com.financial.product.service;

import com.financial.product.dto.ProductDetailResponseDTO;
import com.financial.product.dto.ProductListDto;
import com.financial.product.entity.Product;
import com.financial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public Slice<ProductListDto> allProductList(Pageable pageable, String sort){
        Slice<ProductListDto> products = productRepository.allProductPage(pageable, sort);

        return products;
    }

    public ProductDetailResponseDTO productsDetail(Long productId) {
        Product findProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException());
        ProductDetailResponseDTO detailResponse = new ProductDetailResponseDTO(findProduct);
        return detailResponse;
    }
}
