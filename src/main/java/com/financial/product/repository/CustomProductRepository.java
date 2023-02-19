package com.financial.product.repository;

import com.financial.product.dto.ProductListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomProductRepository {


    Slice<ProductListDto> allProductPage(Pageable pageable , String sort);
}
