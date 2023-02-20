//package com.financial.product.dto;
//
//import com.financial.product.entity.Product;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class ProductListDto {
//
//    private Long productId;
//
//    private String bankName;
//
//    private String productName;
//
//    private String content;
//
//    private LocalDate productMakeDay;
//
//    private List<InterestDto> interests = new ArrayList<>();
//
//
//
//    public ProductListDto(Product product) {
//        this.productId = product.getId();
//        this.bankName = product.getBankName().getBankName();
//        this.productName = product.getProductName();
//        this.content = product.getContent();
//        this.interests = product.getInterests().stream().map(interest -> new InterestDto(interest)).collect(Collectors.toList());
//        this.productMakeDay = product.getProductMakeDay();
//    }
//}
