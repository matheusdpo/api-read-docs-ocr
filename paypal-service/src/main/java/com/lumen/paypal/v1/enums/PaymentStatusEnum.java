package com.lumen.paypal.v1.enums;

public enum PaymentStatusEnum {

    CREATED("CREATED"),
    APPROVED("APPROVED"),
    DECLINED("DECLINED"),
    CANCELLED("CANCELLED"),
    ERROR("ERROR"),
    REFUNDED("REFUNDED"),
    COMPLETED("COMPLETED");

    String paymentStatus;

    PaymentStatusEnum(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
