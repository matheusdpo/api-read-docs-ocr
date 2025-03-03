package com.lumen.api.v1.models.requests.api;

public class RequestBodyOcrModel {
    private String country;

    private String documentType;

    private String imageBase64;


    public String getCountry() {
        return country;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}