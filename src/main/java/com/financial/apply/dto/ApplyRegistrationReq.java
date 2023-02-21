package com.financial.apply.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApplyRegistrationReq {
    @ApiModelProperty(name = "상품 id", dataType = "Long", example = "1")
    private Long productId;
    @ApiModelProperty(name = "상품의 이자 id", dataType = "Long",example = "1")
    private Long interestId;
}
