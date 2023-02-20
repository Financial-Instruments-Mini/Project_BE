package com.financial.product.service.serviceImpl;

import com.financial.product.dto.KeywordDTO;
import com.financial.product.entity.Product;
import com.financial.product.entity.enums.Keyword;

import com.financial.product.repository.ProductRepository;
import com.financial.product.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {

    private final ProductRepository productRepository;

    @Override
    public List<KeywordDTO> searchKeyword(Keyword keyword){
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(product -> product.getKeyword().equals(keyword)) // 키워드로 필터링 (아직 엔티티임)
                .map(product -> new KeywordDTO(product))// 엔티티를 DTO로 바꿔줌
                .collect(Collectors.toList()); // 그 DTO를 모아서 List로 반환함
    }


}
