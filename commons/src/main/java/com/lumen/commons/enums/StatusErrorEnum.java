package com.lumen.commons.enums;

public enum StatusErrorEnum {

    PARAMS_NULL_OR_EMPTY("Params is null or empty"),
    COUNTRY_IS_NOT_AVAILABLE("Country is not available yet"),
    DOC_IS_NOT_AVAILABLE("Document type is not available"),
    DOC_IS_NOT_VALID("Document type is not valid for this country"),
    DOC_MODEL_NOT_FOUND("Document model not found"),
    BASE64_ERROR("Error on encode image to base64"),
    UNKOWN_ERROR("Unknown error"),
    API_KEY_IS_REQUIRED("API Key is required"),
    API_KEY_IS_NOT_VALID("API Key is not valid"),
    FILE_IS_EMPTY("File is empty"),
    FILE_SIZE_IS_NOT_VALID("File size is not valid"),
    FILE_EXTENSION_IS_NOT_VALID("File extension is not valid"),
    USER_INACTIVE("User is not active"),
    USER_EXIST("User already exists"),
    EMAIL_EXIST("Email already exists"),
    PHONE_EXIST("Phone already exists"),
    TOKEN_INVALID("Token is invalid"), USER_NOT_FOUND("User not found"),
    LIMIT_EXCEED("Your limit has been exceeded");

    StatusErrorEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private final String errorMessage;

    public String getError() {
        return errorMessage;
    }
}
