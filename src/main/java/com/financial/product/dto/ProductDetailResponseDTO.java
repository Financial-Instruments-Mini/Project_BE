package com.financial.product.dto;

import com.financial.interest.dto.InterestResponseDTO;
import com.financial.interest.entity.Interest;
import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.Product;
import com.financial.product.entity.enums.BankName;
import com.financial.product.entity.enums.JoinWay;
import com.financial.product.entity.enums.Keyword;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDetailResponseDTO {


    @ApiModelProperty(name = "id", dataType = "Long", example = "1")
    private Long id;

    @ApiModelProperty(name = "productType", dataType = "String", example = "SAVING")
    private String productType;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private String bankName;

    @ApiModelProperty(name = "productName", dataType = "String", example = "신한 미래 농부 적금")
    private String productName;

    @ApiModelProperty(name = "joinWay", dataType = "String", example = "INTERNET")
    private String joinWay;

    @ApiModelProperty(name = "content", dataType = "String", example = "급여 하나로 OK! 월복리로 이자에 이자가 OK!")
    private String content;

    @ApiModelProperty(name = "job", dataType = "String", example = "HOUSEWIFE")
    private String job;

    @ApiModelProperty(name = "productMakeDay", dataType = "LocalDate", example = "2023-02-16")
    private LocalDate productMakeDay;

    @ApiModelProperty(name = "keyword", dataType = "String", example = "2023-02-16")
    private String keyword;

    @ApiModelProperty(name = "minimumAmount", dataType = "Long", example = "5000000")
    private Long minimumAmount;

    @ApiModelProperty(name = "maxLimit", dataType = "Long", example = "10000000")
    private Long maxLimit;

    @ApiModelProperty(name = "interests")
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
