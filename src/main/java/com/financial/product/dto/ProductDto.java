package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import com.financial.product.entity.enums.BankName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    @ApiModelProperty(name = "productId", dataType = "Long", example = "1")
    private Long productId;

    @ApiModelProperty(name = "bankName", dataType = "String", example = "국민")
    private BankName bankName;

    @ApiModelProperty(name = "productName", dataType = "String", example = "신한 미래 농부 적금")
    private String productName;

    @ApiModelProperty(name = "content", dataType = "String", example = "급여 하나로 OK! 월복리로 이자에 이자가 OK!")
    private String content;

    @ApiModelProperty(name = "interests")
    private List<Interest> interests = new ArrayList<>();

}
