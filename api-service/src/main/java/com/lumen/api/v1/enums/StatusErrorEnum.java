package com.lumen.api.v1.enums;

public enum StatusErrorEnum {

    PARAMS_NULL_OR_EMPTY(1, "Params is null or empty"),
    COUNTRY_IS_NOT_AVAILABLE(2, "Country is not available yet"),
    DOC_IS_NOT_AVAILABLE(3, "Document type is not available"),
    DOC_IS_NOT_VALID(4, "Document type is not valid for this country"),
    DOC_MODEL_NOT_FOUND(5, "Document model not found"),
    BASE64_ERROR(6, "Error on encode image to base64"),
    UNKOWN_ERROR(99, "Unknown error");

    StatusErrorEnum(Integer id, String errorMessage) {
        this.id = id;
        this.errorMessage = errorMessage;
    }

    private final Integer id;
    private final String errorMessage;

    public Integer getId() {
        return id;
    }

    public String getError() {
        return errorMessage;
    }
}
