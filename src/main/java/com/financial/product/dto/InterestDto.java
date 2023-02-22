package com.financial.product.dto;

import com.financial.interest.entity.Interest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InterestDto {

    @ApiModelProperty(name = "InterestId", dataType = "Long", example = "1")
    private Long id;

    @ApiModelProperty(name = "dueDate", dataType = "int", example = "1")
    private int dueDate;

    @ApiModelProperty(name = "rate", dataType = "Double", example = "3.0")
    private Double rate;


    public InterestDto(Interest interest) {
        this.id = interest.getId();
        this.dueDate = interest.getDueDate().getInterest();
        this.rate = interest.getRate();
    }
}
