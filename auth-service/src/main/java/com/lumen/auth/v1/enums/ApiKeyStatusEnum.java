package com.lumen.auth.v1.enums;

public enum ApiKeyStatusEnum {

    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");

    String statusApiKey;

    ApiKeyStatusEnum(String statusApiKey) {
        this.statusApiKey = statusApiKey;
    }

    public String getStatusApiKey() {
        return statusApiKey;
    }
}
