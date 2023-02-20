package com.financial.product.repository;

import com.financial.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {

    Slice<Product> findProductByProductNameContaining(Pageable pageable,String productName);


}
