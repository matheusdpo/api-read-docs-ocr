package com.lumen.api.v1.models.responses.api;

public class ResponseCheckSignatureModel {
    private boolean isSigned;


    @Override
    public String toString() {
        return "{" +
                "isSigned=" + isSigned +
                '}';
    }

    // Getters e Setters

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }
}
