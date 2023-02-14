package com.financial.product.entity.enums;

public enum InterestRate {
    TWELVE(12), TWENTY_FOUR(24);

    private int interest;

    InterestRate(int interest) {
        this.interest = interest;
    }

    public int getInterest() {
        return interest;
    }
}
