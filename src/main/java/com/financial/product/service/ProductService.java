package com.financial.product.service;

import com.financial.interest.dto.InterestByAll;
import com.financial.interest.entity.Interest;
import com.financial.interest.repository.InterestRepository;
import com.financial.product.dto.ProductDetailResponseDTO;
//import com.financial.product.dto.ProductListDto;
import com.financial.product.dto.ProductFindResDTO;
import com.financial.product.entity.Product;
import com.financial.product.entity.enums.DueDate;
import com.financial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final InterestRepository interestRepository;

//    public Slice<ProductListDto> allProductList(Pageable pageable, String sort){
//        Slice<ProductListDto> products = productRepository.allProductPage(pageable, sort);
//
//        return products;
//    }

    public ProductDetailResponseDTO productsDetail(Long productId) {
        Product findProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException());
        ProductDetailResponseDTO detailResponse = new ProductDetailResponseDTO(findProduct);
        return detailResponse;
    }

    public Slice<InterestByAll> findAllProductSortByInterest(Pageable pageable) {
        Slice<Interest> orderByRateDesc = interestRepository.findAllByDueDateOrderByRateDesc(pageable ,DueDate.TWENTY_FOUR);
        Slice<InterestByAll> sortedProduct = orderByRateDesc.map(interest -> new InterestByAll(interest));
        return sortedProduct;

    }

    public Slice<InterestByAll> findALlProductSortByMakeDay(Pageable pageable) {
        Slice<Interest> orderByProductMakeDay = interestRepository.findAllByDueDateOrderByProductMakeDay(pageable, DueDate.TWENTY_FOUR);
        Slice<InterestByAll> sortedProduct = orderByProductMakeDay.map(interest -> new InterestByAll(interest));
        return sortedProduct;
    }

    public Slice<ProductFindResDTO> findProductSearch(Pageable pageable, String search) {
        Slice<Product> findProducts = productRepository.findProductByProductNameContaining(pageable,search);
        Slice<ProductFindResDTO> findProductsDto = findProducts.map(product -> new ProductFindResDTO(product));
        return findProductsDto;
    }
}
