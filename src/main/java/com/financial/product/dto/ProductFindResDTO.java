package com.financial.product.dto;

import com.financial.product.entity.Product;
import com.financial.product.entity.enums.BankName;
import lombok.Data;

@Data
public class ProductFindResDTO {
    private Long id;
    private String productName;
    private String bankName;


    public ProductFindResDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.bankName = product.getBankName().getBankName();
    }
}
