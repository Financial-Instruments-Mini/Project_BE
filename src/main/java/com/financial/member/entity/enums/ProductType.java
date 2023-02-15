package com.financial.member.entity.enums;

public enum ProductType {
    DEPOSIT("예금"), SAVING("적금"), DEPOSIT_AND_SAVING("예금/적금");

    private String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
