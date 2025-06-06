package com.lumen.commons.enums;

public enum StatusBillingTypeEnum {
    PAY_AS_YOU_GO("PAY AS YOU GO"),
    MONTHLY_SUBSCRIPTION("MONTHLY SUBSCRIPTION"),
    ANNUALY_SUBSCRIPTION("ANNUALY SUBSCRIPTION");

    private String status;

    StatusBillingTypeEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
