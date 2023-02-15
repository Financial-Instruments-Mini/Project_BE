package com.financial.product.entity.enums;

public enum DueDate {
    TWELVE(12), TWENTY_FOUR(24);

    private int interest;

    DueDate(int interest) {
        this.interest = interest;
    }

    public int getInterest() {
        return interest;
    }
}
