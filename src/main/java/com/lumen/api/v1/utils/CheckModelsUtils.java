package com.lumen.api.v1.utils;

import com.lumen.api.v1.enums.CountriesEnum;
import com.lumen.api.v1.enums.DocumentTypeEnum;
import com.lumen.api.v1.models.RequestBodyOcrModel;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CheckModelsUtils {

    public static boolean isRequestParamsOcrNullOrEmpty(RequestBodyOcrModel requestBodyOcrModel) {
        if (Objects.isNull(requestBodyOcrModel.getCountry()) || StringUtils.isEmpty(requestBodyOcrModel.getCountry())) {
            return true;
        }

        if (Objects.isNull(requestBodyOcrModel.getDocumentType()) || StringUtils.isEmpty(requestBodyOcrModel.getDocumentType())) {
            return true;
        }

        if (Objects.isNull(requestBodyOcrModel.getImageBase64()) || StringUtils.isEmpty(requestBodyOcrModel.getImageBase64())) {
            return true;
        }

        return false;
    }

    public static boolean isValidDocumentForCountry(String documentType, String country) {
        // Converte os valores para o formato do enum
        DocumentTypeEnum docType = DocumentTypeEnum.valueOf(documentType.toUpperCase());
        CountriesEnum countryEnum = CountriesEnum.valueOf(country.toUpperCase());

        // Valida as combinações de documento e país
        return switch (countryEnum) {
            case BRAZIL -> docType == DocumentTypeEnum.RG || docType == DocumentTypeEnum.CPF;
            case USA, CANADA -> docType == DocumentTypeEnum.ID || docType == DocumentTypeEnum.PASSPORT;
            default -> false;
        };

    }
}
