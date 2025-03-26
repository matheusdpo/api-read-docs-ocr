package com.lumen.paypal.v1.enums;

public enum PaymentStatusEnum {

    CREATED("CREATED"),
    DECLINED("DECLINED"),
    CANCELLED("CANCELLED"),
    ERROR("ERROR"),
    REFUNDED("REFUNDED"),
    APPROVED("APPROVED");

    String paymentStatus;

    PaymentStatusEnum(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
