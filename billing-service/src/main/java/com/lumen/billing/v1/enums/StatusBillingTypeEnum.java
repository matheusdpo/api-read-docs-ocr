package com.lumen.billing.v1.enums;

public enum StatusBillingTypeEnum {
    PAY_AS_YOU_GO("PAY AS YOU GO"),
    MONTHLY_SUBSCRIPTION("MONTHLY SUBSCRIPTION"),
    ANNUALY_SUBSCRIPTION("ANNUALY SUBSCRIPTION"),
    LATE_PAYMENT("LATE PAYMENT"),
    CREDIT("CREDIT"),
    OTHER("OTHER");

    private String status;

    StatusBillingTypeEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
