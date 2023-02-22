package com.financial.apply.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ApplyRegistrationReq {
    @ApiModelProperty(name = "상품 id", dataType = "Long", example = "1")
    @NotNull(message = "productId는 필수 입력 값입니다.")
    private Long productId;
    @ApiModelProperty(name = "상품의 이자 id", dataType = "Long",example = "1")
    @NotNull(message = "interestId 필수 입력 값입니다.")
    private Long interestId;
}
