package com.financial.member.dto;

import com.financial.member.entity.enums.Job;
import com.financial.member.entity.enums.ProductType;
import com.financial.product.entity.enums.BankName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequest {


    private String password;
    private String phoneNumber;
    private ProductType productType;
    private Job job;
    private BankName bankName;

}
