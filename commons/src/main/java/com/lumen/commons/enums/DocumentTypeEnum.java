package com.lumen.commons.enums;

public enum DocumentTypeEnum {

    RG("RG"),
    CPF("CPF"),
    CNH("CNH"),
    ID("ID"),
    CIN("CIN"),
    CTPS("CTPS"),
    TITULO("TITULO"),
    CERTIDAONASCIMENTO("CERTIDAONASCIMENTO"),
    CARTAOMILITAR("CARTAOMILITAR"),
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
