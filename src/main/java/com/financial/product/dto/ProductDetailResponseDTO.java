package com.financial.product.dto;

import com.financial.interest.dto.InterestResponseDTO;
import com.financial.interest.entity.Interest;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.Product;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import com.financial.product.entity.enums.Keyword;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDetailResponseDTO {

    private Long id;

    private String productType;

    private String bankName;

    private String productName;

    private String joinWay;

    private String content;

    private String job;

    private LocalDate productMakeDay;

    private String keyword;

    private Long minimumAmount;

    private Long maxLimit;

    private List<InterestResponseDTO> interests = new ArrayList<>();

    public ProductDetailResponseDTO(Product product) {
        this.id = product.getId();
        this.productType = product.getProductType().getType();
        this.bankName = product.getBankName().getBankName();
        this.productName = product.getProductName();
        this.joinWay = product.getJoinWay().getJoinWay();
        this.content = product.getContent();
        this.job = product.getJob().getJobName();
        this.productMakeDay = product.getProductMakeDay();
        this.keyword = product.getKeyword().getKeywordType();
        this.minimumAmount = product.getMinimumAmount();
        this.maxLimit = product.getMaxLimit();
        this.interests = product.getInterests().stream().map(interest -> new InterestResponseDTO(interest)).collect(Collectors.toList());
    }
}
