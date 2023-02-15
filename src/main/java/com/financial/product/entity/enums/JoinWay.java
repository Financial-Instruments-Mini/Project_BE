package com.financial.product.entity.enums;

public enum JoinWay {
    PHONE("휴대폰"), BANK("직접 방문"), INTERNET("인터넷");

    private String joinWay;

    JoinWay(String joinWay) {
        this.joinWay = joinWay;
    }

    public String getJoinWay() {
        return joinWay;
    }
}
