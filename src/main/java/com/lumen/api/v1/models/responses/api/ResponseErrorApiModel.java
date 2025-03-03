package com.lumen.api.v1.models.responses.api;

public class ResponseErrorApiModel {
    private final boolean isError = true;
    private Integer status;
    private String message;

    public ResponseErrorApiModel(Integer status, String message) {
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
