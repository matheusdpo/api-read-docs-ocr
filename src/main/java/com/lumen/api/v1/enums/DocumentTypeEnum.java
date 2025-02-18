package com.lumen.api.v1.enums;

public enum DocumentTypeEnum {

    RG("RG"),
    CPF("CPF"),
    ID("ID"),
    DNI("DNI"),
    PASSPORT("PASSPORT");

    private final String document;

    DocumentTypeEnum(String document) {
        this.document = document;
    }

    public static boolean isDocumentEqualsEnum(String document) {
        for (DocumentTypeEnum documentTypeEnum : DocumentTypeEnum.values()) {
            if (documentTypeEnum.name().equalsIgnoreCase(document)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return document;
    }
}
