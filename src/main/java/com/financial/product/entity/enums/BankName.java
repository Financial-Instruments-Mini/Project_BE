package com.financial.product.entity.enums;

public enum BankName {
    SHIN_HAN("신한"), KOOK_MIN("국민"), WOO_RIE("우리"), HA_NA("하나");

    private String bankName;
    BankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankName() {
        return bankName;
    }
}
