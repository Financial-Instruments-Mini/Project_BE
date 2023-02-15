package com.financial.product.entity.enums;

public enum Keyword {
    MAIN_BANK("주거래 은행"), YOUTH_PREFERENTIAL_TREATMENT("청년 우대"), HOUSING_SUBSCRIPTION("주택 청약")
    , PREPARING_FOR_OLD_AGE("노후 준비");

    private String keywordType;

    Keyword(String keywordType) {
        this.keywordType = keywordType;
    }

    public String getKeywordType() {
        return keywordType;
    }
}
