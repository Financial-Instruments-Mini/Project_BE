package com.financial.interest.dto;

import com.financial.interest.entity.Interest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InterestResponseDTO {

    @ApiModelProperty(name = "id",dataType = "Long", example = "1")
    private Long id;

    @ApiModelProperty(name = "dueDate",dataType = "int", example = "TWELVE")
    private int dueDate;

    @ApiModelProperty(name = "rate", example = "2.5")
    private Double rate;

    public InterestResponseDTO(Interest interest) {
        this.id = interest.getId();
        this.dueDate = interest.getDueDate().getInterest();
        this.rate = interest.getRate();
    }
}
