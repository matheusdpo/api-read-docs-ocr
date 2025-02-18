package com.lumen.api.v1.models;

public class ResponseErrorApi {
    private final boolean isError = true;
    private Integer status;
    private String message;

    public ResponseErrorApi(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
