package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.enums.BankName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    private Long productId;

    private BankName bankName;

    private String productName;

    private String content;

    private List<Interest> interests = new ArrayList<>();

}
